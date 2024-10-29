<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>useBean 액션 태크</h2>
	<h3>자바빈즈 생성하기</h3>
	
	<!--  Person person = new Person()   -->
	<jsp:useBean id="person" class="common.Person" scope="request" /> 
	
	<!-- person.setName("임꺽정") -->
	<h3>setProperty 액션 태크로 자바빈즈 속성 지정하기</h3>
	<jsp:setProperty property="name" name="person" value="임꺽정"/>
	<jsp:setProperty property="age" name="person" value="41"/>
	
	
	<h3>getProperty 액션 태크로 자바빈즈 속성 읽기</h3>
	<li>이름 : <jsp:getProperty property="name" name="person"/></li>
	<li>나이 : <jsp:getProperty property="age" name="person"/></li>
	
</body>
</html>