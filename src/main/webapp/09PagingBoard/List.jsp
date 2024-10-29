<%@page import="utils.BoardPage"%>
<%@page import="model1.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
  
<%
	//DB 연결
	BoardDAO dao  = new BoardDAO(application);

	//사용자 입력한 검색 조건을 Map 저장
	Map<String, Object> param = new HashMap();
	String searchField = request.getParameter("searchField");
	String searchWord = request.getParameter("searchWord");
	
	if(searchWord != null){
		param.put("searchField",searchField);
		param.put("searchWord",searchWord);
	}
	
	int totalCount = dao.selectCount(param);  //111
	
	//페이징 처리
	int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE")); //10
	int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));  //5
	int totalPage = (int)Math.ceil(totalCount / (double)pageSize);   //12
	
	//현재 페이지 확인
	int pageNum  = 1;                        //6
	String pageTemp = request.getParameter("pageNum"); 
	System.out.println("pageTemp => " + pageTemp);
	
	if(pageTemp != null && !pageTemp.equals("")){
		pageNum = Integer.parseInt(pageTemp);  //6
	}
	
	//각 page 출력될 범위
	int start = (pageNum -1) * pageSize +1;    // 51
	int end = pageNum * pageSize;   //60
	
	param.put("start", start);  //51
	param.put("end", end);   //60
	
	List<BoardDTO> boardLists = dao.selectListPage(param);
	dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<h2>목록 보기(List) - 현재 페이지: <%=pageNum%>(전체 : <%=totalPage %>) </h2>
	
	<!-- form태크 action이 없다면  어디로 가냐? ==> 직전화면으로 이동    -->
	<form method="get">
		<table border="1" width="90%">
			<tr>
				<td align="center">
					<select name="searchField">
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
					<input type="text" name="searchWord"> 
					<input type="submit" value="검색하기"> 
				</td>
			</tr>
		</table>
	</form>
	
	<table border="1" width="90%">
		<tr>
			<th width="10%">번호</th>
			<th width="50%">제목</th>
			<th width="15%">작성자</th>
			<th width="10%">조회수</th>
			<th width="15%">작성일</th>
		</tr>
		
		<%
			if(boardLists.isEmpty()){
				//게시글 없을 때
		%>
			<tr>
				<td colspan="5" align="center">
					등록된 게시물이 없습니다.^^*
				</td>
			</tr>
		<%
			}else{
				//게시글 있을 때
				int virtualNum = 0;
				int countNum = 0;
				
				for(BoardDTO dto :boardLists){
					
			//		virtualNum = totalCount--;
			//                    111              5 *10+0 =50  
					virtualNum = totalCount - (((pageNum-1)*pageSize)+countNum++);
		%>
					<tr align="center">
						<td><%= virtualNum%></td>
						<td align="left"><a href="View.jsp?num=<%=dto.getNum()%>"><%=dto.getTitle() %></a></td>
						<td><%=dto.getId() %></td>
						<td><%=dto.getVisitcount() %></td>
						<td><%=dto.getPostdate() %></td>
					</tr>
		<%
				}
			}
		%>
	</table>
	
	<!-- 목록 하단의 [글쓰기] 버튼  -->
	<table border="1" width="90%">
		<tr align="right">
			<td>
				<%= BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI()) %>
			</td>
			<td>
				<button type="button" onclick="location.href='Write.jsp';">글쓰기</button>
			</td>		
		</tr>
	</table>
</body>
</html>