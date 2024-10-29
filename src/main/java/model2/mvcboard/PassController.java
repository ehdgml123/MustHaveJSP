package model2.mvcboard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

import java.io.IOException;

import fileupload.FileUtil;

@WebServlet("/mvcboard/pass.do")
public class PassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
   
    public PassController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	request.setAttribute("mode", request.getParameter("mode"));
		
		request.getRequestDispatcher("/14MVCBoard/Pass.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String mode = request.getParameter("mode");
		String idx = request.getParameter("idx");
		String pass = request.getParameter("pass");
		
		MVCBoardDAO dao = new MVCBoardDAO();
		
	    boolean confirmed = dao.confirmPassword(pass, idx);
	
	    if(confirmed) {
	    	if(mode.equals("edit")) {  //수정
	    		
	    		HttpSession session = request.getSession();
	    		
	    		//비밀번호를 session 저장
	    		session.setAttribute("pass", pass);
	    		
	    		response.sendRedirect("../mvcboard/edit.do?idx="+idx);
	    		
	    	}else if(mode.equals("delete")) { //삭제
	    		
	    		// 용도 ? ==> 저정된 파일명 획득하기 위해서 
	    		MVCBoardDTO dto = dao.selectView(idx);
	    		
	    		int result = dao.deletePost(idx);
	    		
	    		if(result == 1) {
	    			 String savedFileName = dto.getSfile();
	    			 FileUtil.deleteFile(request, "/Uploads", savedFileName);
	    			 JSFunction.alertLocation(response, "삭제되었습니다", "../mvcboard/list.do");
	    		}
	    		
	    	} // end delete
	    	
	    }else {
	    	
	    	 JSFunction.alertBack(response, "비밀번호 검증에 실패했습니다.");
	    	 
	    } //end confirmed
	    
	} //end doPost

}
