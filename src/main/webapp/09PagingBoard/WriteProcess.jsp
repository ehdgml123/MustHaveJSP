<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./IsLoggedIn.jsp"%>

<%
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	BoardDTO dto = new BoardDTO();
	dto.setTitle(title);
	dto.setContent(content);
	dto.setId(session.getAttribute("UserId").toString());
	
	//DB 연결
	
	BoardDAO dao = new BoardDAO(application);
	int iResult = dao.insertWrite(dto);
	
	if(iResult == 1){
		//성공 -> PRG의해서 redirect 한다.(하지않으면 도배!!)
		response.sendRedirect("List.jsp");
	}else{
		//실패
		JSFunction.alertBack("글쓰기에 실패하였습니다.", out);
	} 
	
	/* int iResult=0;
	for(int i=0; i<=100; i++){
		System.out.println("---------processing-----------------");
		dto.setTitle(title + "_" + i);
		dto.setContent(content + "_" + i);
		iResult = dao.insertWrite(dto);
	} */
	
	dao.close();
%>