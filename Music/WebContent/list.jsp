<%@page import="bean.ArticlePage"%>
<%@page import="service.ArticlePageService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String pageStr = request.getParameter("page");
	int pageNum = 1;

	if (pageStr != null && pageStr.length() > 0) {
		pageNum = Integer.parseInt(pageStr);
	}

	ArticlePageService service = ArticlePageService.getInstance();
	ArticlePage articlePage = service.getArticlePage(pageNum);

	request.setAttribute("articlePage", articlePage);
%>
<jsp:forward page="list_view.jsp" />

