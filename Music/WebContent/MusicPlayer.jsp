<%@page import="dto.PlayDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<script type="text/javascript" src="./resources/js/player2.js"></script>
<link rel="stylesheet" href="./resources/css/frame.css">
<link rel="stylesheet" href="./resources/css/player.css">
<style type="text/css">
      html, body { height: 100%; }

      .custom-context-menu {
      	z-index: 999;
        position: absolute;
        box-sizing: border-box;
        min-height: 100px;
        min-width: 200px;
        background-color: #ffffff;
        box-shadow: 0 0 1px 2px lightgrey;
      }
      
      .custom-context-menu ul {
        list-style: none;
        padding: 0;
        background-color: transparent;
      }
      
      .custom-context-menu li {
        padding: 3px 5px;
        cursor: pointer;
      }
      
      .custom-context-menu li:hover {
        background-color: #f0f0f0;
      }    
    </style>
</head>

<body>
<%
	String playing = (String)session.getAttribute("musicName");
	ArrayList<PlayDto> playList = (ArrayList<PlayDto>) session.getAttribute("playList");
%>

<div id="musicPlayer" class="frameSkin">
   <div id="frame1">
        <form action="PlayerServlet" method="post" name="controller" id="act">
		<input type="button" name="mp" class="button2" id="play" onclick="what('play')">
		<input type="button" name="mp" class="button2" id="pause" onclick="what('pause');">
		<input type="button" name="mp" class="button2" id="resume" onclick="what('resume');">
		<input type="button" name="mp" class="button2" id="stop" onclick="what('stop');" >
		<input type="button" name="mp" class="button2" id="volumeup" onclick="what('volumeup');">
		<input type="button" name="mp" class="button2" id="volumedown" onclick="what('volumedown');">
		<input type="button" name="mp" class="button2" id="previous" onclick="what('previous');">
		<input type="button" name="mp" class="button2" id="next" onclick="what('next');">
		<input type="hidden" name="musicName" class="button2" value="">
		<input type="hidden" name="waht" class="button2" value="">
	</form>
   </div>
   
   <%
   
   if(session.getAttribute("member") == null) {
	%>
	<div id="frameplay">
   		<%-- <input type="text" name="songName" id="playing" value="<%=playing %>" readonly="readonly" > --%>
   		<marquee id="playing" direction="left" loop="infinity">로그인이 필요합니다...</marquee>
   		<%-- <marquee id="playing" direction="left" loop="infinity">....</marquee> --%>
    </div>
	<%		   
   }
   else if(playing == null) {
   %>
   <div id="frameplay">
   		<%-- <input type="text" name="songName" id="playing" value="<%=playing %>" readonly="readonly" > --%>
   		<marquee id="playing" direction="left" loop="infinity">노래를 재생해주세요...</marquee>
   		<%-- <marquee id="playing" direction="left" loop="infinity">....</marquee> --%>
   </div>
  <%
   }else{
  %>
  <div id="frameplay">
   		<%-- <input type="text" name="songName" id="playing" value="<%=playing %>" readonly="readonly" > --%>
   		<marquee id="playing" direction="left" loop="infinity"><%=playing%></marquee>
   		<%-- <marquee id="playing" direction="left" loop="infinity">....</marquee> --%>
   </div>
  <%} %>
   
   <div id="frame2">
        
       	<form action="" method="post" name="music">
		<% if(playList != null) {%>
		<table id="list">
		<%	for(int i=0; i<playList.size();i++) {
		%>
			<tr>
				<td>
					<input type="button" class="aqwe" name="musicN" 
					value="<%=playList.get(i).getPlayMusicName()%>" 
					onClick="selectMusic(this,<%=playList.size()%>)"
					oncontextmenu="handleCreateContextMenu(event,'<%=playList.get(i).getPlayMusicNum()%>','<%=playList.get(i).getPlayNum()%>');">
				</td>
			</tr>
		
		</table>
		<%
			}
		} else {
		%>	
		<table id="list">
		<tr>
               <td>
                   <input type="text" readonly="readonly" value="로그인 후 이용 가능합니다.">
               </td>
            </tr>
        <tr>
		</table>
		<%	
		}
		%>

	</form>
   </div>
	
	<div id='context_menu' class="custom-context-menu" style="display: none;">
      <ul>
        <li><a>삭제</a></li>
      </ul>
    </div>

</div>
<script type="text/javascript" src="./resources/js/player.js"></script>
</body>
</html>