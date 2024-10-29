<%@page import="model1.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//List.jsp?searchField=content&searchWord=%EB%82%B4%EC%9A%A92
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
	//System.out.println(" searchField => " + searchField);
	//System.out.println(" searchWord => " + searchWord);
	
	int totalCount = dao.selectCount(param);
	List<BoardDTO> boardLists = dao.selectList(param);
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
	<h2>목록 보기(List)</h2>
	
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
				//계시글 있을 때
				int virtualNum = 0;
				for(BoardDTO dto :boardLists){
					virtualNum = totalCount--;
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
				<button type="button" onclick="location.href='Write.jsp';">글쓰기</button>
			</td>		
		</tr>
	</table>
</body>
</html>