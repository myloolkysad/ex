<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="./resources/img/favicon.png">
<link rel="stylesheet" href="./resources/css/findidpw.css">
<script type="text/javascript" src="./resources/js/findidpw.js"></script>
</head>
<body onresize="parent.resizeTo(470,400)" onload="parent.resizeTo(470,400)">
  <div id="find">
   <div id="theme">
       성명과 휴대폰 번호을 입력해주세요.
   </div>
   
    <div id="findemail">
    <form action="FindServlet"  method="post" name="find">
        <p>이름 : <input id="emailtext1" type="text" name="username" placeholder="이름입력"></p>
        <p>휴대폰 번호 : <input id="emailtext2" type="text" name="userphone" placeholder="휴대폰번호 -제외입력"></p>
        <input id="emailbt" type="submit" value="전송">
    </form>
    </div>
  </div>
</body>
</html>