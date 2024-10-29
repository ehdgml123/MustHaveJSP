package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import memberShip.MemberDAO;
import memberShip.MemberDTO;

import java.io.IOException;

@WebServlet("/MemberAuth.mvc")
public class MemberAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberDAO dao;

	@Override
	public void init() throws ServletException {
		
		System.out.println("------------init()---------------------");
		ServletContext app = this.getServletContext();
		
		String dirver = app.getInitParameter("OracleDriver");
		String connectUrl = app.getInitParameter("OracleURL");
		String oId = app.getInitParameter("OralceId");
		String oPass = app.getInitParameter("OraclePwd");
		
		dao = new MemberDAO(dirver, connectUrl, oId, oPass);
	}

	@Override
	public void destroy() {
		System.out.println("------------ destroy()---------------------");
		dao.close();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("------------ service()---------------------");
		
		String admin_id = this.getInitParameter("admin_id");
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDTO memberDTO = dao.getMemberDTO(id, pass);
		
		String memberName = memberDTO.getName();
		
		if(memberName != null) {
			request.setAttribute("authMessage", memberName + " 회원님 방가방가");
		}else {
			if(admin_id.equals(id)) {
				request.setAttribute("authMessage", admin_id + "는 관리자 입니다.");
			}else {
				request.setAttribute("authMessage", "귀하는 비회원 입니다.");
			}
		}
		
		request.getRequestDispatcher("/12Servlet/MemberAuth.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
