<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원 추가 테스트(executeUpdate() 사용)</h2>
<%
	JDBConnect jdbc = new JDBConnect();

	String id = "test4";
	String pass = "1111";
	String name = "테스트4회원";
	
	String sql = "insert into member(id, pass,name, regidate) values(?, ?, ?, sysdate) ";
	
	PreparedStatement pstmt = jdbc.conn.prepareStatement(sql);
	System.out.println("pstmt :" + pstmt);
	pstmt.setString(1, id);
	pstmt.setString(2, pass);
	pstmt.setString(3, name);
	
	int inResult = pstmt.executeUpdate();  //저장성공 : 1, 실패:0
	out.println(inResult + "행이 입력되었습니다.");
	
	jdbc.close();	
	
	
%>
</body>
</html>