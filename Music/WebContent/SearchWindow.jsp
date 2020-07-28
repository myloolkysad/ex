<%@page import="dto.MusicDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="conn.music.AlbumList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<link rel="shortcut icon" href="./resources/img/favicon.png">
<link rel="stylesheet" href="./resources/css/searchWindow.css">
<style type="text/css">
td .btn {
	width: 60px;
	height: 60px;
	opacity: 0.5;
	background: transparent;
	border: 0px;
}

button .btnImages {
	border: 0px;
	width: 40px;
	height: 40px;
	background: transparent;
}
</style>
<script type="text/javascript">
	function se(){
		var i = document.getElementsByClassName("searchWord_text")[0].value;
		window.location.href = "SearchWindow.jsp?what="+i;
	}
	
	function playSong(a) {
		var url="playSong.jsp?Name="+a;
		window.open(url, "음악담기", "width=300,height=200,left=450,top=200");
	}
	
	function innerSong(o) {
		var url="innerSong.jsp?Num="+o;
		window.open(url, "음악담기", "width=300,height=200,left=450,top=200");
	}
	
	function enterkey() {
        if (window.event.keyCode == 13) {
		// 엔터키가 눌렸을 때 실행할 내용
             se();
        }
	}
</script>
</head>
<body>
    <div id="searchWindow">
      <div id="searchWindow_top">
      <div id="searchWord_label">검색 단어 : </div>
        <input class="searchWord_text" type="search" placeholder="검색 단어 입력" onkeyup="enterkey()">
       <input class="searchWord_button" type="button" value="검색" onclick="se();">
      </div>
      <hr>
      <div id="searchWindow_bottom">
          <table style="width:100%">
          	<tr>
          		<th class="list1">곡정보</th>
          		<th class="list2">앨범</th>
          		<th class="list3">듣기</th>
          		<th class="list4">담기</th>
          	</tr>
          	<%
          		String what = request.getParameter("what");
          		System.out.println("search윈도우쪽 :: "+what);
          		AlbumList list = new AlbumList();
          		ArrayList<MusicDto> search = list.searchAlbums(what);
          		for(int i=0; i<search.size();i++) {
          	%>
				<tr>
					<td class="list1" align="center">
						 <%=search.get(i).getMusicName() %>
					</td>
					
					<td class="list2" align="center"> 
						 <%=search.get(i).getMusicAlbum() %>
					</td>
					
					<td class="list3"><button type="button" id="playbtn1"
							class="btn btnEvent">
							<img src="./resources/img/play.png" alt="btnImages"
								class="btnImages" 
								onclick="playSong('<%=search.get(i).getMusicName()%>');" style="cursor:pointer;">
						</button>
					</td>
					
					<td class="list4">
						<button type="button" id="plusbtn1" class="btn btnEvent">
							<img src="./resources/img/plus.png" alt="btnImages"
								class="btnImages"
								onclick="innerSong(<%=search.get(i).getMusicNum()%>);" style="cursor:pointer;">
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