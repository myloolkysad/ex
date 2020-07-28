<%@page import="musicPlayer.PlayerServletM"%>
<%@page import="conn.play.PlaySearchServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>음악재생</title>
</head>
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
		
		PlayerServletM player = null;
		boolean run = true;
		
		if(session.getAttribute("member") != null) {
			player = new PlayerServletM(musicName);
			player.start();
			
		}else{
			out.println("로그인 후 이용해주세요.");
		}
		
	%>




	<form>
		<input type="button" value="닫기" onclick="exit();">
	</form>
</body>
</html>