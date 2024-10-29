<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./IsLoggedIn.jsp"%>

<%
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	BoardDTO boardDTO = new BoardDTO();
	boardDTO.setTitle(title);
	boardDTO.setContent(content);
	boardDTO.setId(session.getAttribute("UserId").toString());
	
	//DB 연결
	
	BoardDAO dao = new BoardDAO(application);
	int iResult = dao.insertWrite(boardDTO);
	
	if(iResult == 1){
		//성공 -> PRG의해서 redirect 한다.(하지않으면 도배!!)
		response.sendRedirect("List.jsp");
	}else{
		//실패
		JSFunction.alertBack("글쓰기에 실패하였습니다.", out);
	}
	
	dao.close();
%>