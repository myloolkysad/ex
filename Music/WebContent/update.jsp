<%@page import="service.ArticleUpdateService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="article" class="bean.Article" />
<jsp:setProperty property="*" name="article" />
<%
	int articleId = Integer.parseInt(request.getParameter("articleId"));
	article.setId(articleId);
 
	
	String viewPage = "";
	try{
		ArticleUpdateService.getInstance().update(article);
		viewPage = "update_success.jsp";
		
	}catch(Exception e){
		request.setAttribute("exception", e);
		viewPage = "update_error.jsp";
	}
%>

<jsp:forward page="<%=viewPage%>"/>

