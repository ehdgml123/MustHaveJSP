package model2.mvcboard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/mvcboard/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ViewController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("--------------- view Controller -----------------------");
		MVCBoardDAO dao = new MVCBoardDAO();
		
		String idx = request.getParameter("idx");
		
		//조회수 증가
		dao.updateVisitCount(idx);
		
		//idx해당하는 데이터 추가
		MVCBoardDTO dto = dao.selectView(idx);
		dao.close();
		
		// 줄바꿈 처리
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
		//첨부파일 확장자 추출 및 이미지 타입 확인
		String ext = null;
		String fileName = dto.getSfile();
		
		// 로이 : idx 155 기준
		if(fileName !=null) {
			ext = (fileName.substring(fileName.lastIndexOf(".")+1)).toLowerCase();
		}
		
		String[] mimeStr = {"png","jpg","gif","jpge"};
		
		List<String> mimeList = Arrays.asList(mimeStr);
		
		boolean isImage = false;
		
		if(mimeList.contains(ext)) {
			isImage = true;
			
		}
		
		request.setAttribute("dto", dto);
		request.setAttribute("isImage", isImage);
		request.getRequestDispatcher("/14MVCBoard/View.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
