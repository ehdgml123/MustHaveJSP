<%@ page import="java.util.Date"%>
<%@ page import="common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> %> --%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head><title>JSTL - set 1</title></head>
<body>
    <!-- 변수 선언 -->
    <c:set var="directVar" value="10000000" />
    <c:set var="elVar" value="${ directVar mod 5}" />
    <c:set var="expVar" value="<%= new Date() %>" />
    <c:set var="betweenVar">변수값 요렇게 설정</c:set>
    <c:set var="betweenVar2" value="변수값 요렇게 설정2"></c:set>

	<%-- <fmt:formatDate value="${registered}" pattern="yyyy-MM-dd" /> --%>
    <h4>EL을 이용해 변수 출력</h4>
    <ul>
    	<%-- <fmt:formatNumber value="50000.345" pattern="###,###.00" /> --%>
        <li>directVar : ${ directVar }</li>
        
        <li>elVar : ${ elVar }</li>
        <li>expVar : ${ expVar }</li>
        <li>expVar : <fmt:formatDate value="${expVar}" pattern="yyyy-MM-dd" /></li>
        <li>betweenVar : ${ betweenVar }</li>
        <li>betweenVar2 : ${ betweenVar2 }</li>
    </ul>
    
   <!--  Person personVar1 = new Person("박문수", 50) -->
    <h4>자바빈즈 생성 1 - 생성자 사용</h4>
    <c:set var="personVar1" value='<%= new Person("박문수", 50) %>'
           scope="request" />
    <ul>
        <li>이름 : ${ personVar1.name }</li>
        <li>나이 : ${ personVar1.age}</li>
    </ul>
 
    <h4>자바빈즈 생성 2 - target, property 사용</h4>
    <c:set var="personVar2" value="<%= new Person() %>" scope="request" />
    <c:set target="${personVar2 }" property="name" value="정약용" />
    <c:set target="${personVar2 }" property="age" value="60" /> 
    <ul>
        <li>이름 : ${ personVar2.name }</li>
        <li>나이 : ${ requestScope.personVar2.age }</li>
    </ul>
</body>
</html>