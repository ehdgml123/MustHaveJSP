package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/12Servlet/HelloServlet.do")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---------------doGet----------------------");
		request.setAttribute("message", "Hello Servlet....");
		
//		RequestDispatcher dis = request.getRequestDispatcher("12Servlet/HelloServlet.jsp");
//		dis.forward(request, response);
		
//		request.getRequestDispatcher("/12Servlet/HelloServlet.jsp").forward(request, response);
		request.getRequestDispatcher("HelloServlet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
