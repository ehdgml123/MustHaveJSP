<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  //날짜 표시 형식

	long creationTime = session.getCreationTime();
	
	//System.out.println(creationTime);
	
	String creationTimeStr = dateFormat.format(new Date(creationTime));  //최초 요청(섹션 생성) 시각
	//System.out.println(creationTimeStr);
	
	long lastTime = session.getLastAccessedTime();                     //마지막 요청 시각
	String dastTimeStr = dateFormat.format(new Date(lastTime));
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Session 설정 확인</h2>
	<ul>
		<li>세션 유지 시간 : <%=session.getMaxInactiveInterval() %> </li>
		<li>세션 아이디 : <%=session.getId() %> </li>
		<li>최초 요청 시간 : <%=creationTimeStr %> </li>
		<li>마지막 요청 시각 : <%=dastTimeStr %> </li>
	</ul>
</body>
</html>