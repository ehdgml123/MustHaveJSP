<%@page import="memberShip.MemberDAO"%>
<%@page import="memberShip.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("user_id");  //must1
	String userPwd = request.getParameter("user_pw"); //1234
	
	String oracleDirver = application.getInitParameter("OracleDriver");
	String oracleURL = application.getInitParameter("OracleURL");
	String oracleId = application.getInitParameter("OralceId");
	String oraclePwd = application.getInitParameter("OraclePwd");
	
	//DB연결
	MemberDAO dao = new MemberDAO(oracleDirver, oracleURL, oracleId, oraclePwd);
	
	//id,pass 전달해서 회원정보 획득(회원 없으면 null)
	MemberDTO memberDTO = dao.getMemberDTO(userId, userPwd);  //null
	dao.close();  //DB 연결 종료
	
	if(memberDTO.getId() != null){
		//로그인 성공
		session.setAttribute("UserId", memberDTO.getId());
		session.setAttribute("UserName", memberDTO.getName());
		
		//브라우저가 재요청
		response.sendRedirect("LoginForm.jsp");
		
	}else{
		//로그인 실패
		request.setAttribute("LoginErrMsg", "로그인 오류입니다.");
		
		//forword..........
		request.getRequestDispatcher("LoginForm.jsp").forward(request, response);	
	}
	
%>
