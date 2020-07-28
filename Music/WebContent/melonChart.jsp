<%@page import="Crawling.SongVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Crawling.NewVO"%>
<%@page import="Crawling.Crawling"%>
<%@page import="Crawling.CrawlingNew"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<%
		Crawling craw = new Crawling();
		ArrayList<SongVO> lo = craw.crawlingStart();
		
		CrawlingNew cra = new CrawlingNew();
		ArrayList<NewVO> loa = cra.crawlingStart1();
	%>
	
	<%
// 		for(int i=0; i<lo.size(); i++) {
// 			out.print("노래제목: "+lo.get(i).getName()+"<br>");
// 			out.print("가수명: "+lo.get(i).getArtist()+"<br>");
// 			out.print("앨범명: "+lo.get(i).getAlbum()+"<br>");
// 			out.print("ff"+lo.get(i).getSrc()+"<br>");
// 			out.print("=========================="+"<br>");
// 		}
	for(int i=0; i<loa.size(); i++) {
		out.print("노래제목: "+loa.get(i).getName()+"<br>");
		out.print("가수명: "+loa.get(i).getArtist()+"<br>");
		out.print("앨범명: "+loa.get(i).getAlbum()+"<br>");
		out.print("ff"+loa.get(i).getSrc()+"<br>");
		out.print("=========================="+"<br>");
	}
	%>
</body>
</html>