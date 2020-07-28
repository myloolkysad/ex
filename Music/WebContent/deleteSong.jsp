<%@page import="conn.music.MusicDeleteServlet"%>
<%@page import="musicPlayer.PlayerServletM"%>
<%@page import="conn.play.PlaySearchServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>음악재생</title>
</head>
<script type="text/javascript">
opener.location.reload();
</script>
<script type="text/javascript">
	function exit() {
		window.close();	
	}
</script>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	String musicName = request.getParameter("Name");
	String artist = request.getParameter("art");
	
	MusicDeleteServlet md = new MusicDeleteServlet();
	md.deleteSong(musicName, artist);
	%>




	<form>
		<input type="button" value="닫기" onclick="exit();">
	</form>
</body>
</html>