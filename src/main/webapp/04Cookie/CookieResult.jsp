<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>1. 쿠키값 확인하기(쿠키가 생성된 이후의 페이지 이동)</h2>
<%
	 Cookie[] cookies =request.getCookies();
	
	if(cookies != null){
		for(Cookie c : cookies){
			 String cookieName = c.getName();
			 String cookieValue = c.getValue();
			 
			 out.println(String.format("쿠키명: %s - 쿠키값:  %s <br>", cookieName, cookieValue));					 
		}
	}
%>
</body>
</html>