<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>JSTL - forEach2</title></head>
<body>
   <%
   		String[] rgba = {"Red", "Green","Blue","Black"};
   	
   		List<String> list = new ArrayList<String>();
   		list.add("Red1");
   		list.add("Green2");
   		list.add("Blue3");
   		list.add("Black4");
   %>
   
   <h4>향상된 for문 형태의 forEach 태크</h4>
   <c:forEach items="<%=rgba %>" var="color">
   		<span style="color:${color};">${color}</span>
   </c:forEach>
   <hr>
   
   <h4>varStatus 속성 살펴보기</h4>
   		<table border="1">
   			<c:forEach items="<%= list%>" var="color" varStatus="loop">
   			<tr>
   				<td>count: ${loop.count}</td>	
   				<td>index: ${loop.index}</td>	
   				<td>current: ${loop.current}</td>	
   				<td>first: ${loop.first}</td>	
   				<td>last: ${loop.last }</td>	
   			</tr>
   			</c:forEach>
   		</table> 
</body>
</html>
