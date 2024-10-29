<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="utils.JSFunction"%>
<%@page import="java.io.FileNotFoundException"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String originalFileName = request.getParameter("oName");
	String savedFileName = request.getParameter("sName");
	String saveDirectory = application.getRealPath("/Uploads");
	
	try{
		
		//C:\Works\jsp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MustHaveJSP\Uploads\20241028_111133911.PNG
		File file = new File(saveDirectory, savedFileName); //경로설정
		InputStream inStream = new FileInputStream(file);
		
		//한글 파일명 깨짐 방지
		/*
		String client = request.getHeader("User-Agent");		
		System.out.println("client ==> " + client);
		if(client.indexOf("WOW64")  == -1 ){
			originalFileName = new String(originalFileName.getBytes("utf-8"), "ISO-8859-1");
		}else{
			originalFileName = new String(originalFileName.getBytes("utf-8"), "utf-8");
		}*/
		
		//파일 다운로드용 응답헤더 설정
		response.reset();
		response.setContentType("application/octet-stream");
		
		String encodedFileName = URLEncoder.encode(originalFileName, "UTF-8").replaceAll("\\+", "%20");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
		response.setHeader("Content-Length", ""+ file.length());
		
		//출력 초기화
		out.clear();
		
		//출력 스트림 생성
		OutputStream outStream = response.getOutputStream();
		
		//출력 스트림에 파일 내용 출력 -> byte배열 생성
		byte[] b = new byte[(int)file.length()];  //100
		
		int readBuffer = 0;
		int count=0;
		while((readBuffer = inStream.read(b))>0){
			System.out.println("읽는 횟수 : " + count++);
			outStream.write(b, 0, readBuffer);
		}
		
		inStream.close();
		outStream.close();
	
	}catch(FileNotFoundException e){
		JSFunction.alertBack("파일을 찾을 수 없습니다.", out);
	}catch(Exception e){
		JSFunction.alertBack("예외가 발생하였습니다.", out);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>