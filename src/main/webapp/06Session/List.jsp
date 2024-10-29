<%@page import="memberShip.MemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="memberShip.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

String oracleDirver = application.getInitParameter("OracleDriver");
String oracleURL = application.getInitParameter("OracleURL");
String oracleId = application.getInitParameter("OralceId");
String oraclePwd = application.getInitParameter("OraclePwd");

//DB연결
MemberDAO dao = new MemberDAO(oracleDirver, oracleURL, oracleId, oraclePwd);
List<MemberDTO> list = dao.getAllList();
%>

<table border="1">
	<tr>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>가입일</th>
	<tr>
	<%
		for(MemberDTO dto : list){
	%>
	<tr>
		<td><%=dto.getId() %></td>
		<td><%=dto.getPass() %></td>
		<td><%=dto.getName() %></td>
		<td><%=dto.getRegidage() %></td>
	</tr>
	<%
		}
	 %>
</table>
<a href="LoginForm.jsp">로그인 화면 이동</a>
</body>
</html>