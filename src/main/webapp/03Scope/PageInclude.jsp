<%@page import="common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Include 페이지</h4>
<%
	int pInteger2 = (Integer)pageContext.getAttribute("pageInteger");
	//String pString = (String)pageContext.getAttribute("pageString");
	Person pPerson2 = (Person)pageContext.getAttribute("pagePerson");	
%>

	<ul>
		<li><%= pInteger2%></li>
		<li><%= pageContext.getAttribute("pageString")%></li>
		<li><%= pPerson2.getName()%>, <%= pPerson2.getAge()%></li>
	</ul>
</body>
</html>