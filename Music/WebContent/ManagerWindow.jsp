<%@page import="dto.MusicDto"%>
<%@page import="conn.music.AlbumList"%>
<%@page import="dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="conn.member.MemberSearchServlet"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="./resources/img/favicon.png">
<link rel="stylesheet" href="./resources/css/managerWindow.css">
<link rel="stylesheet" href="./resources/css/frame.css">
<script type="text/javascript">
	function closeA(){
		window.close();
	}
	
	function addMusic() {
		
		var singer = document.newMusic.singer.value;
		var song = document.newMusic.song.value;
		var album = document.newMusic.album.value;
		
		if(singer == "" || singer == null) {
			alert("가수명을 입력해주세요");
			singer.select();
			singer.focus();
			return false;
		}
		
		if(song == "" || song == null) {
			alert("노래명을 입력해주세요");
			return false;
		}
		
		if(album == "" || album == null) {
			alert("앨범명을 입력해주세요");
			album.select();
			album.focus();
			return false;
		}
		
		document.newMusic.submit();
	}
	
	function deleteMusic123() {

		var arrNumber = new Array();
		var arrNumber2 = new Array();
		
		var checkedMusicName = document.getElementsByName("che");
        for(var i=0; i<checkedMusicName.length; i++) {
        	if(checkedMusicName[i].checked == true) {
        		var song = document.getElementsByClassName("music1e")[i].innerText;
        		var Artist = document.getElementsByClassName("music2e")[i].innerText;
        		arrNumber.push(song);
        		arrNumber2.push(Artist);
        	}
        }
        
        for(var i=0;i<arrNumber.length;i++) {
        	alert(arrNumber[i]);
        	alert(arrNumber2[i]);
        }
	}
	
	function deleteMusic(a,b) {
		var url="deleteSong.jsp?Name="+a+"&art="+b;
		window.open(url, "음악삭제", "width=300,height=200,left=450,top=200");
    }
	
</script>
<title>회원관리</title>
</head>
<body>
<div id="manager">
   <div id="theme">
      <label id="title">
          관리자창
      </label>
       <div id="close">
        <input id="closebt" class="button1" type="button" value="닫기" onclick="closeA()">
    </div>
   </div>
    <div id="frameleft">
        <div id="left1">
            <div id="leftTitle">
               <label class="a">회원관리</label>
                <table style="width: 100%" id="member1">
                    <tr>
                    <th class="member1">회원 ID</th>
                    <th class="member2">회원권 여부</th>
                </tr>
                </table>
            </div>
            <div id="leftFile">
               <table style="width: 100%">
               <% 
               	MemberSearchServlet memberS = new MemberSearchServlet();
               	ArrayList<MemberDto> memberList = memberS.getMemberList();  
               	for(int i=0; i<memberList.size(); i++){
               %>
                <tr>
                    <td class="member1"><%=memberList.get(i).getMemberid()%></td>
                    <td class="member2"><%=memberList.get(i).getMemberFreeTicket()%></td>
                </tr>
               <%
               	}
               %>
               
               
               </table>
            </div>
        </div>
    </div>
    <div id="frameright">
       <div id="right1">
            <div id="rightTitle">
                          <label class="a">음원관리</label>
                <table style="width: 100%" id="member2">
                    <tr>
                    <th class="ch" >선택</th>
                    <th class="music1">곡정보</th>
                    <th class="music2">가수명</th>
                </tr>
                </table>
            </div>
            <div id="rightFile">
                
                <table style="width: 100%">
                <%
					AlbumList list = new AlbumList();
					ArrayList<MusicDto> albumList = list.getAlbums();
					for(int i=0; i<albumList.size();i++) {
				%> 
                <tr>
                    <td class="che"><input type="button" name="che" value="삭제" 
                    onclick=
                    "deleteMusic('<%=albumList.get(i).getMusicName()%>','<%=albumList.get(i).getMusicArtist()%>');"
                    ></td>
                    <td class="music1e"><%=albumList.get(i).getMusicName()%></td>
                    <td class="music2e"><%=albumList.get(i).getMusicArtist()%></td>
                </tr>   
               <%
					}
               %>
               
               </table>
           </div>
        </div>
        
        
        <div id="right2">
        
          <form name="newMusic" action="MusicInsertServlet" class="form-horizontal" method="post" enctype="multipart/form-data">	
        	<div class="filetext">
        		<p><label class="textlist">가수명 : </label>
        		<input type="text" class="textli" placeholder="가수명 입력" name="singer">
        		<p><label class="textlist">노래명 : </label>
        		<input type="text" class="textli" placeholder="노래명 입력" name="song"
        		readonly="readonly" id="songName">
        		<p><label class="textlist">앨범명 : </label>
        		<input type="text" class="textli" placeholder="앨범명 입력" name="album">
        	</div>
        	<div class="filebox">
        		<label for="upload">업로드</label>
           		 <input id="upload" class="button1" type="file" accept=".mp3" 
           		 onchange="javascript: document.getElementById('songName').value = this.value.split('/').pop().split('\\').pop().split('.').shift();"
           		 name="file">
        	</div>
        	<div class="filebt">
        		<input id="save2" class="button1" type="button" value="음원관리 저장" onclick="addMusic();">
           		<input type="hidden" name="what" value="">
        	</div>
          </form>
       
       
       
       
        </div>
    </div>
</div>
</body>
</html>