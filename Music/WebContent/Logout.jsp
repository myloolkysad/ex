<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<link rel="stylesheet" href="./resources/css/logout.css">

<title>Insert title here</title>
</head>
<body>
	<%
	MemberDto member = (MemberDto) session.getAttribute("member");
	String id = member.getMemberid();
	String qw = member.getMemberFreeTicket();
	%>
	<div class="logout">
		<div>
			<p id="idtext">
				<b><%=id%></b> 님 환영합니다.
			<p id="qwtext">이용권 만료일 :<%=qw%>
			<form action="LogoutServlet" method="post">
				<p><input id="logout" type="submit" value="로그아웃">
			</form>
		</div>
		<div>
			<form action="Mypage.jsp" method="post">
				<p><input id="mypage" type="submit" value="마이페이지">
			</form>
		</div>
	</div>

</body>
</html>