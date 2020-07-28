<%@page import="java.util.StringTokenizer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="Testcon" class="dao.Testcon"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />

</head>
<body>
<div class="jumbotron">
<div class="container">
			<h1 class="display-3">
		
<%
	String time=Testcon.returnTime();

	StringTokenizer st= new StringTokenizer(time,",");
	String firstTime = (String)st.nextElement();
	String secondTime= (String)st.nextElement();

%>
</h1>
</div>
</div>
첫번째 시간은 <%=firstTime%>입니다<br>
두번째 DbconnectionMGr파일은 <%=secondTime%>입니다.<br>

두 시간차는 <%= Long.parseLong(secondTime)-Long.parseLong(firstTime)%>입니다.<br>
</body>
</html>