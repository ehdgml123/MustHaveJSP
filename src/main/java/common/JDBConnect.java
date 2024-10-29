package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class JDBConnect {
	
	public Connection conn;
	public Statement stmt;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	public JDBConnect() {
		
		try {
			String driver = "oracle.jdbc.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "musthave";
			String pwd = "1234";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(디폴트 생성자)");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public JDBConnect(ServletContext application) {
		
		try {
	
			String driver  = application.getInitParameter("OracleDriver");
			String url = application.getInitParameter("OracleURL");
			String id = application.getInitParameter("OralceId");
			String pwd = application.getInitParameter("OraclePwd");
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(인수 생성자1)");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public JDBConnect(String driver, String url, String id, String pwd) {
		
		
		  try {
			  Class.forName("oracle.jdbc.OracleDriver");
		  
			  conn = DriverManager.getConnection(url, id, pwd);
			  
		  }catch(Exception e) { e.printStackTrace(); }
	}
	
	//자원 반납
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
			
			System.out.println("JDBC 자원 해제");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
