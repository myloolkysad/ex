<%@page import="Crawling.Crawling"%>
<%@page import="Crawling.SongVO"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="./resources/css/tablist.css">
<style type="text/css">
 .album{
 	font-size: 12px;
}
th,td {
    border-bottom: 1px solid #444444;
}
td .btn{
  width: 60px; height: 60px;
  	opacity: 0.5;
	background: transparent;
	border: 0px;
}
button .btnImages{
	border:0px;
  	width: 40px; height: 40px;
  	background: transparent;
}
</style>
</head>
<body>

	<div class="total">
		<div class="main">
			<%-- <%@ include file="melonChart.jsp" %> --%>
			<table style="width: 100%">
				<tr>
					<th>image</th>
					<th>곡정보</th>
					<th>앨범명</th>
					<th>듣기</th>
					<th>담기</th>
				</tr>
				<!--lo.get(i).getName()는 곡명 /  lo.get(i).getArtist()는 가수명 /  lo.get(i).getAlbum()는 앨범명 -->
				<!--j의 값을 생성하고 2의 배수를 한 이유는 스크롤링한 태그(a태그 내부)의 지정범위에 2개의 태그가 있어 그중 첫번째 태그만 받아오기 위함이다.-->

				<%
				Crawling cra = new Crawling();
				ArrayList<SongVO> lo = cra.crawlingStart();
				int pageNum = 0;
				for(int i=0; i<50; i++) {
				if((i % 19) == 0)pageNum++;
				int j=2*i;
			%>
				<tr class="list" id="list<%=i%>">
					<td><%=lo.get(j).getSrc()%></td>
					<td><%=lo.get(i).getName()%><p class="album">
							&ensp;&ensp;&ensp;
							<%=lo.get(i).getArtist() %></p></td>
					<td><%=lo.get(i).getAlbum()%></td>
					<td><button type="button" id="playbtn<%=i%>"
							class="btn btnEvent">
							<img src="./resources/img/play.png" alt="btnImages"
								class="btnImages">
						</button></td>
					<td><button type="button" id="plusbtn<%=i%>"
							class="btn btnEvent">
							<img src="./resources/img/plus.png" alt="btnImages"
								class="btnImages">
						</button></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div class="listtext">
			<%
			for(int i=1;i<pageNum+1;i++){
			%>
			<a href="javascript:showPage(<%=i%>)" id="pageNum"><%=i%>&nbsp;</a>
			<%
			}
			%>
		</div>
	</div>
	<script type="text/javascript" src="./resources/js/tab_Popular.js"></script>
</body>
</html>