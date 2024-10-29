package model2.mvcboard;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------ListController.get--------------------");
		
		MVCBoardDAO dao = new MVCBoardDAO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		
		if(searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		
		//게시물 개수
		int totalCount = dao.selectCount(map); 
		System.out.println("totalCount : " + totalCount);
		
		//페이징 처리
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE")); //10
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));  //5
		int totalPage = (int)Math.ceil(totalCount / (double)pageSize);   //12

		System.out.println("totalPage : " + totalPage);
		
		//현재 페이지 확인
		int pageNum  = 1;                        //6
		String pageTemp = request.getParameter("pageNum"); 
		System.out.println("pageTemp => " + pageTemp);
		
		if(pageTemp != null && !pageTemp.equals("")){
			pageNum = Integer.parseInt(pageTemp);  //6
		}
		
		//각 page 출력될 범위
		int start = (pageNum -1) * pageSize +1;    // 0
		int end = pageNum * pageSize;   //10
		
		map.put("start", start);  //0
		map.put("end", end);   //10
		
		
		//게시판 조건 맞는 전체 데이타 검색
		List<MVCBoardDTO> boardLists = dao.selectListPage(map);
		
		dao.close();
		
		//뷰에 전달할 매개변수 추가
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "/mvcboard/list.do");
		map.put("pagingImg", pagingImg);  // 1
		map.put("totalCount", totalCount);  //5
		map.put("pageSize", pageSize);   //10
		map.put("pageNum", pageNum);   //1
		
		//전달할 데이터를 request 영역에 저장 후 List.jsp로 포워드
		request.setAttribute("boardLists",boardLists );
		request.setAttribute("map", map);
		
		//포워드
		request.getRequestDispatcher("/14MVCBoard/List.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------ListController.post--------------------");
		doGet(request, response);
	}

}
