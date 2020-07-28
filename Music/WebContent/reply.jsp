<%@page import="service.ArticleReplyService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="reply" class="bean.Article" />
<jsp:setProperty property="*" name="reply" />
<% 
	int parentId = Integer.parseInt(request.getParameter("parentId"));
	String viewPage = null;
	int reply_id = 0;
	try {
		reply_id = ArticleReplyService.getInstance().reply(parentId, reply);
		System.out.println(reply_id+"리플라이아이디");
		viewPage = "reply_success.jsp";
	} catch (Exception e) {
		viewPage = "reply_error.jsp";
		request.setAttribute("exception", e);
	}
%>
<jsp:forward page="<%=viewPage%>">
	<jsp:param value="<%=reply_id%>" name="articleId"/>
</jsp:forward>