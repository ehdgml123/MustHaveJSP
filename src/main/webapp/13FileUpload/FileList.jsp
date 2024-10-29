<%@page import="fileupload.MyFileDTO"%>
<%@page import="java.util.List"%>
<%@page import="fileupload.MyFileDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>DB에 등록된 파일 목록 보기</h2>
	<a href="FileUploadMain.jsp">파일등록1</a>
	<a href="MultiuploadMain.jsp">파일등록2</a>
	
	<%
		MyFileDAO dao = new MyFileDAO();
		List<MyFileDTO> fileLists = dao.myFileList();
		dao.close();
	%>
	
	<table border="1">
		<tr>
			<td>No</td><td>제목</td><td>카테고리</td>
			<td>원본 파일명</td><td>저장된 파일명</td><td>작성일</td>
		</tr>
		<%
			for(MyFileDTO f : fileLists){
		%>
			<tr>
				<td><%=f.getIdx() %></td>
				<td><%=f.getTitle() %></td>
				<td><%=f.getCate() %></td>
				<td><%=f.getOfile() %></td>
				<td><%=f.getSfile() %></td>
				<td><%=f.getPostdate() %></td>
				<td><a href="Download.jsp?oName=<%= URLEncoder.encode(f.getOfile(), "utf-8")%>
				&sName=<%= URLEncoder.encode(f.getSfile(), "utf-8")%>">[다운로드]</a>
				</td>
			</tr>
		<%		
			}
		%>
		
	</table>
</body>
</html>