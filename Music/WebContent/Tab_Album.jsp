<%@page import="dto.MemberDto"%>
<%@page import="conn.music.AlbumList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.MusicDto"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="./resources/css/tablist.css">
<link rel="stylesheet" href="./resources/css/album.css">
</head>
<script type="text/javascript">
	function playSong(a) {
		var url="playSong.jsp?Name="+a;
		window.open(url, "음악담기", "width=300,height=200,left=450,top=200");
	}
	
	function innerSong(o) {
		var url="innerSong.jsp?Num="+o;
		window.open(url, "음악담기", "width=300,height=200,left=450,top=200");
	}
</script>
<body>
	<div class="total">
		<div class="main1">
			<table class="albumTable">
				<tr class="albumTR">
					<th class="th2">이미지</th>
					<th class="th3">곡정보</th>
					<th class="th4">앨범명</th>
					<th class="th5">듣기</th>
					<th class="th6">담기</th>
				</tr>
				
				<%
					AlbumList list = new AlbumList();
					ArrayList<MusicDto> albumList = list.getAlbums();
					for(int i=0; i<albumList.size();i++) {
				%>
				
				<tr>
					<td class="th2">이미지src넣는 곳</td>
					<td class="th3"><%=albumList.get(i).getMusicName() %>
					<p><%=albumList.get(i).getMusicArtist() %></td>
					<td class="th4"><%=albumList.get(i).getMusicAlbum() %></td>
					
					<td class="th5">
						<button type="button" class="btn btnEvent">
							<img src="./resources/img/play.png" name="playbtn" alt="btnImages"	class="btnImages"
							onclick="playSong('<%=albumList.get(i).getMusicName()%>');" style="cursor:pointer;">
						</button>
					</td>
					
					<td class="th6">
						<button type="button" id="plusbtn1" class="btn btnEvent">
							<img src="./resources/img/plus.png" name="listbtn" alt="btnImages" class="btnImages" 
							onclick="innerSong(<%=albumList.get(i).getMusicNum()%>);" style="cursor:pointer;">
						</button>
					</td>
					
					
				</tr>
				
				<%
					}
				%>

			</table>
		</div>
	</div>
</body>
</html>