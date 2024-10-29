<%@page import="model1.board.BoardDTO"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String num = request.getParameter("num");

	//DB 연결
	BoardDAO dao = new BoardDAO(application);
	
	//조회수 증가
	dao.updateVisitCount(num);
	//num해당하는 레코드 넘겨죠!
	BoardDTO dto = dao.selectView(num);
	dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function deletePost(){
		let confirmed = confirm("정말로 삭제하겠습니까?");
		
		if(confirmed){
			let form = document.writeFrm;
			form.method = "post";
			form.action = "DeleteProcess.jsp";
			form.submit();
		}
	}
</script>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<h2>회원제 게시판 - 상세 보기(View)</h2>
	<form name="writeFrm">
	
		<input type="hidden" name="num" value="<%=num%>">
	
		<table border="1" width="90%">
			<tr>
				<td>번호</td>
				<td><%=dto.getNum() %></td>
				<td>작성자</td>
				<td><%=dto.getName() %></td>
			</tr>
			<tr>
				<td>작성일</td>
				<td><%=dto.getPostdate() %></td>
				<td>조회수</td>
				<td><%=dto.getVisitcount() %></td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3"><%=dto.getTitle()%></td>				
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3"><%=dto.getContent()%></td>				
			</tr>
			<tr>
				<td colspan="4" align="center">
					<%
						//UserId null값이 아니고, 로그인 UserId하고 게시글 작성하고 같니?
						if(session.getAttribute("UserId") != null && 
							session.getAttribute("UserId").toString().equals(dto.getId())){
					%>
					<button type="button" onclick="location.href='Edit.jsp?num=<%=dto.getNum()%>'">수정하기</button>
					
					<button type="button" onclick="deletePost()">삭제하기</button>
					
				<%-- 	<button type="button" onclick="location.href='DeleteProcess.jsp?num=<%=dto.getNum()%>'">삭제하기</button> --%>
					<%		
						}
					%>
					<button type="button" onclick="location.href='List.jsp'">목록보기</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>






