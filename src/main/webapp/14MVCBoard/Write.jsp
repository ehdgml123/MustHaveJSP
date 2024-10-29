<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파일 첨부형 게시판 - 글쓰기(write)</h2>
	<form method="post" name="writeFrm" enctype="multipart/form-data">
	
	     
	
		<table border="1" width="90%">
			<tr>
				<td>작성자</td>
				<td><input type="text" name="name" style="width:150px;"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" style="width:90%;"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" style="width:90%;height=100px;"></textarea></td>
			</tr>
			<tr>
				<td>첨부 파일</td>
				<td><input type="file" name="attachedFile"></td>
			</tr>
			<tr>
				<td>비빌번호</td>
				<td><input type="text" name="pass" style="width:90%;"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">작성완료</button>
					<button type="reset">다시작성</button>
					<button type="button" onclick="location.href='../mvcboard/list.do';">목록 바로가기</button>
				</td>				
			</tr>
		</table>
	</form>
</body>
</html>