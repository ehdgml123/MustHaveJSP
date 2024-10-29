<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp" %>
<%
	String num = request.getParameter("num");
	
	BoardDAO dao = new BoardDAO(application);
	
	//지금 가지고 있는 값은 num(기본키)만 있음..
	BoardDTO dto = dao.selectView(num);
	
	
	String sessionId = session.getAttribute("UserId").toString();
	
	if(sessionId.equals(dto.getId())){
		int iResult = dao.deletePost(dto.getNum());
		
		if(iResult == 1){
			//삭제 성공
			JSFunction.alertLocation("삭제되었습니다.", "List.jsp", out);
		}else{
			JSFunction.alertBack("삭제하기에 실패했습니다.", out);
		}
	}else{
		JSFunction.alertBack("본인만 삭제할 수 있습니다.", out);
		return ;
	}
	
	
	
	
%>