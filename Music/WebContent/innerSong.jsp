<%@page import="conn.play.PlaySearchServlet"%>
<%@page import="dto.PlayDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="conn.play.PlayInsertServlet"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
opener.location.reload();
</script>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	
	if(session.getAttribute("member") != null) {
	MemberDto member = (MemberDto) session.getAttribute("member");
	String userid = member.getMemberid();
	String musicNum = (String)request.getParameter("Num");
	
	PlayInsertServlet pi = new PlayInsertServlet();
	pi.innerSong(userid, musicNum);
	
	PlaySearchServlet playSearch = new PlaySearchServlet();
	ArrayList<PlayDto>playList = playSearch.getplayList(userid);
	session.setAttribute("playList", playList);
	
	out.println("재생목록에 추가되었습니다.");
	}

	else {
		out.println("로그인 후 이용해주세요.");
	}
%>

