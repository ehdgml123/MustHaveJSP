package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/12Servlet/DirectServletPrint.do")
public class DirectSetvletPrint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out =  response.getWriter();
		
		out.println("<html>");
		out.println("<head><title>DirectServletPrint</title></head>");
		out.println("<body>");
		out.println("<h2>서블릿에서 직접 출력합니다.</h2>");
		out.println("<p>jsp로 포워드하지 않습니다.</p>");
		out.println("</body>");
		out.println("</html>");
	}

}
