<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<meta charset="utf-8">
<link rel="shortcut icon" href="./resources/img/favicon.png">
<link rel="stylesheet" href="./resources/css/main.css">
<title>음악</title>
</head>
<body>
<div class="backA">
    <div class="subFrame">
        <div class="LogoFrame"><a href="#">
        <img width="95" height="90" src="./resources/img/favicon.png" alt="logo"></a></div>
        <div class="SearchFrame"> <jsp:include page="Searcher.jsp"/></div>
        <div class="TapFrame"><jsp:include page="Tab.jsp" /> </div>
        <div class="BulletinFrame"><jsp:include page="Button.jsp" /> </div>
        <div class="LoginFrame"><jsp:include page="Logout.jsp" /></div>
		<div class="MusicFrame"><jsp:include page="MusicPlayer.jsp" /></div>
    </div>
</div>
</body>
</html>