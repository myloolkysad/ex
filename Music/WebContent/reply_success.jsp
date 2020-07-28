<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>답변 작성 완료</title>
</head>
<body>
답변글을 작성하였습니다. <br>

<a href="list.jsp?page=${param.page }">목록보기</a>
<a href="list.jsp?articleId=${param.articleId }&page=${param.page}">작성글보기</a>
</body>
</html>