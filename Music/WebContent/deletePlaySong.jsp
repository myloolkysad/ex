<%@page import="dto.PlayDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.MemberDto"%>
<%@page import="conn.play.PlayDeleteServlet"%>
<%@page import="conn.music.MusicDeleteServlet"%>
<%@page import="musicPlayer.PlayerServletM"%>
<%@page import="conn.play.PlaySearchServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>재생목록 삭제</title>
</head>

<script type="text/javascript">
	function exit() {
		opener.location.reload();
		window.close();
	}
</script>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	MemberDto member = (MemberDto) session.getAttribute("member");
	String userid = member.getMemberid();
	String musicNum = request.getParameter("Num");
	String playNum = request.getParameter("Num2");
	
	System.out.println("딜리트플레이송 jsp 뮤직넘 ::"+musicNum);
	System.out.println("딜리트플레이송 jsp userid ::"+userid);
	
	PlayDeleteServlet md = new PlayDeleteServlet();
	String a = md.deletePlaySong(musicNum, userid, playNum);
	
	PlaySearchServlet playSearch = new PlaySearchServlet();
	ArrayList<PlayDto>playList = playSearch.getplayList(userid);
	session.setAttribute("playList", playList);
	
	%>

	<form>
		<%=a%>
		<p><input type="button" value="닫기" onclick="exit();">
	</form>
</body>
</html>