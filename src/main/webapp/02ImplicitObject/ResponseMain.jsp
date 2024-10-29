<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>1. 로그인 폼</h2>
	<%
		String loginErr = request.getParameter("loginErr");
		if (loginErr != null) out.print("로그인 실패");
		
		out.print(request.getParameter("user_id"));
	%>
	
	<form method="post" action="./ResponseLogin.jsp">
		아이디: <input type="text" name="user_id"  value="must"/><br>
		패스워드: <input type="text" name="user_pwd" value="1234"/><br>
		<input type="submit" value="로그인" />
	</form>
	
	<h2>2. HTTP 응답 헤더 설정하기</h2>
	<form action="./ResponseHeader.jsp" method="get">
		날짜 형식: <input type="text" name="add_date" value="2021-10-25 09:00" /><br>
		숫자 형식: <input type="text" name="add_int" value="8282" /><br>
		문자 형식: <input type="text" name="add_str" value="홍길동" /><br>
		<input type='submit' value="응답 헤더 설정 & 출력" />		
	</form>
</body>
</html>