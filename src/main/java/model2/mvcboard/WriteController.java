package model2.mvcboard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JSFunction;

import java.io.IOException;

import fileupload.FileUtil;

public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/14MVCBoard/Write.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = request.getServletContext().getRealPath("/Uploads");
		System.out.println("saveDirectory : " + saveDirectory);
		
		//파일 업로드
		String originalFileName = "";
		try {
			originalFileName  = FileUtil.uploadFile(request, saveDirectory);
		}catch(Exception e) {
			JSFunction.alertLocation(response, "파일 업로드 오류입니다.", "/mvcboard/write.do");
			return;
		}
		
		//폼값에 dto에 저장
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setName(request.getParameter("name"));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setPass(request.getParameter("pass"));
		
		
		///파일이 첨부했으면 실행되는 블럭
		if(originalFileName != "") {
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			dto.setOfile(originalFileName);
			dto.setSfile(savedFileName);
		}
		
		//DB 게시 내용 저장
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.insertWrite(dto);
		
		if(result==1) {
			//저장 성공
			System.out.println("-------------result---------------------");
			response.sendRedirect("/mvcboard/list.do");
		}else {
			JSFunction.alertLocation(response, "글쓰기에 실패했습니다.", "../mvcboard/write.do");
//		    response.sendRedirect("../mvcboard/write.do");
		}
		
	}

}
