<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember == null) {
	%>
		<a href="login.do">로그인</a>
	<%
		} else {
		out.println("<h2>[ " + loginMember.getId() + " ] 님이 접속하였습니다</h2>");
	%>
		<a href="logout.do">로그아웃</a>
	<%
		}
	%>
</body>
</html>