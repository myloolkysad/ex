<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정 완료</title>
</head>
<body>
  글을 수정하였습니다.<br>
<a href="list.jsp?page=${param.page}">[목록보기]</a>
<a href="read.jsp?articleId=${param.articldId}&page=${param.page}">[게시글 읽기]</a>
</body>
</html>