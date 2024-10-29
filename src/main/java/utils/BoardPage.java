package utils;

public class BoardPage {            //   5         10                5              1        ../mvcboard/list.do
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
		String pagingStr = "";
		
		//전 페이지 수 계산                               
		int totalPages = (int)Math.ceil((double)totalCount/pageSize);  //1
		
		//이전 페이지 블록 바로가기  
		int pageTemp = ((pageNum-1)/blockPage)* blockPage + 1;  //1
		
		if(pageTemp !=1) {
			pagingStr += "<a href='"+ reqUrl +"?pageNum=1 '>[첫 페이지]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='"+ reqUrl +"?pageNum="+ (pageTemp-1) + "'>[이전 블록]</a>";
		}
		
		// pagingStr = 1 <a href='List.jsp?pageNum=2'>" + 2 + "</a> <a href='List.jsp?pageNum=3'>" + 3 + "</a>
		//각 페이지 번호 출력
		int blockCount = 1;  //
		while(blockCount<= blockPage && pageTemp <=totalPages) {
			if(pageTemp == pageNum) {
				//현재 페이지는 링크를 걸지 않음
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			}else {
				pagingStr += "&nbsp;<a href='"+ reqUrl + "?pageNum=" + pageTemp + "'>" + pageTemp + "</a>&nbsp;";
			}
			
			pageTemp++;   //2
			blockCount++;  //2
		}
		
		//다음 페이지 블록 바로가기 pageTemp(11) 
		if(pageTemp <= totalPages) {
			pagingStr += "<a href='"+ reqUrl +"?pageNum="+ pageTemp +"'>[다음 블록]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='"+ reqUrl +"?pageNum="+ totalPages +"'>[마지막 페이지]</a>";
			
		}
		
		return pagingStr;
	}
}
 