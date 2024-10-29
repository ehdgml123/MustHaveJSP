<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>JSTL - forEach 1</title></head>
<body>
  <h3>일반 for문 형태의 forEach 태크</h3>
  <c:forEach begin="1" end="10" step="2" var="i">
  	<p>반복 ${i} </p>
  </c:forEach>
  <hr>
  
  <h3>varStatus 속성 살펴보기</h3>
  <table border="1">
  <c:forEach begin="1" end="5" var="i" step="2" varStatus="loop">
	  	<tr>
	  		<td>count: ${loop.count}</td>
	  		<td>index: ${loop.index}</td>
	  		<td>current: ${loop.current}</td>
	  		<td>first: ${loop.first}</td>
	  		<td>last: ${loop.last}</td>
	  	</tr>
    </c:forEach>
  	</table>
  <hr>
  
</body>
</html>