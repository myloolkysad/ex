<%@page import="java.sql.SQLException"%>
<%@page import="bean.Article"%>
<%@page import="service.ArticleReadService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int articleId = Integer.parseInt(request.getParameter("articleId"));
	String viewPage  = null;
	try{
		ArticleReadService service = ArticleReadService.getInstance();
		Article article = service.readArticle(articleId, true);
		request.setAttribute("article", article);
		viewPage = "read_view.jsp";
	}catch(SQLException e){
		viewPage = "read_sql_exception.jsp";
		request.setAttribute("exception", e);
	}catch(Exception ex){
		viewPage = "article_not_found.jsp";
		request.setAttribute("exception", ex);
	}

%>

<jsp:forward page="<%=viewPage %>" />

