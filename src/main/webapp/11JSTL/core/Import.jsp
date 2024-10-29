<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>JSTL - import</title></head>
<body>
    <c:set var="requestVar" value="MustHave" scope="request" />
  
    <c:import url="/11JSTL/inc/OtherPage.jsp" var="contents">
        <c:param name="user_param1" value="JSP2" />
        <c:param name="user_param2" value="기본서2" />
    </c:import>       
    
    <h4>다른 문서 삽입하기</h4>
    ${contents }
    
    <h4>외부 자원 삽입하기</h4>    
    <iframe src="../inc/GoldPage.jsp" style="width:100%;height:600px;"></iframe>
</body>
</html>

<%-- <h4>OtherPage.jsp</h4> 
<ul>
    <li>저장된 값 : ${ requestVar }</li>
    <li>매개변수 1 : ${ param.user_param1 }</li>
    <li>매개변수 2 : ${ param.user_param2 }</li>
</ul> --%>