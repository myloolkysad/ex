<%@page import="service.ArticleDeleteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	
	request.setCharacterEncoding("utf-8");
	int articleId = Integer.parseInt(request.getParameter("articleId"));
	String password = request.getParameter("password");
	
	ArticleDeleteService service = ArticleDeleteService.getInstance();
	
	String viewPage = null;
	 
	try{
		service.deleteArticle(articleId, password);
		viewPage = "delete_success.jsp";
	}catch(Exception e){
		request.setAttribute("exception", e);
		viewPage = "delete_error.jsp";
	}
	
%>

<jsp:forward page="<%=viewPage%>"></jsp:forward>


