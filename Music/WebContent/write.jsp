<%@page import="service.ArticleWriteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  

<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="article" class="bean.Article"/>
<jsp:setProperty property="*" name="article"/>
<%
	ArticleWriteService service = ArticleWriteService.getInstance();
	int articleId = service.write(article);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성 완료</title>
</head>
<body>
게시글을 등록하였습니다. <br>
<a href="list.jsp">[목록으로]</a>
<a href="read.jsp?articleId=<%=articleId%>">[게시글읽기]</a>
</body>
</html>