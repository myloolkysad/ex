<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수정실패</title>
</head>
<body>

	<c:set var="excep" value="${exception.getClass().simpleName}" />
	<c:choose>
		<c:when test="${excep == 'ArticleNotFoundException'}">
	수정할 게시글이 존재하지 않습니다.
	</c:when>
		<c:when test="${excep == 'InvalidPasswordException'}">
		 암호를 잘못 입력하셨습니다.
		 </c:when>
	</c:choose>

</body>
</html>

