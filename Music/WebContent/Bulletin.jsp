<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/bulletin.css">
</head>
<body>
	<div id="bull">
        <div id="bull1">이미지</div>
        <div id="bull2">게시판 </div>
        <div id="bull3"><jsp:include page="Bulletin_Searcher.jsp" /></div>
        <div id="bull4"><jsp:include page="Bulletin_Bottom.jsp"/></div>
   </div>
</body>
</html>