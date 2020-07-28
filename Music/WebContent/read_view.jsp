<%@page import="bean.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	<table>
		<tr>
			<td>제목</td>
			<td>${article.title}</td>
		</tr>

		<tr>
			<td>작성자</td>
			<td>${article.writerName}</td>
		</tr>

		<tr>
			<td>작성일</td>
			<td>${article.postingDate}</td>
		</tr>

		<tr>
			<td>내용</td>
			<td>${article.content}</td>
		</tr>

		<tr>
			<td colspan="2"><a href="list.jsp?page=${param.page}">
					[목록보기] </a> <a
				href="reply_form.jsp?parentId=${article.id}&page=${param.page}">
					[답변쓰기] </a> <a
				href="update_form.jsp?articleId=${article.id}&page=${param.page}">
					[수정하기] </a> <a href="delete_form.jsp?articleId=${article.id}">
					[삭제하기] </a>
		</tr>

	</table>
</body>
</html>