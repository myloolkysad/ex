<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<link rel="stylesheet" href="./resources/css/login.css">

<script type="text/javascript">
	function signUp() {
		var signUp_open = window.open("/MusicWeb/SignUp.jsp", "PopupWin", "width=505,height=585");
	}
	function checkForm() {
		if(document.login.userid.value=="" || document.login.userid.value == null) {
			alert("ID를 입력해주세요");
			document.login.userid.focus();
			return false;
		}
		
		if(document.login.userpw.value=="" || document.login.userpw.value == null) {
			alert("PW를 입력해주세요");
			document.login.userpw.focus();
			return false;
		}
		document.login.submit();
	}
	function Findidpw(){
		var find_open = window.open("/MusicWeb/Findidpw.jsp", "find", "width=458,height=320,left=200,top=100,scrollbars=no,resizable=no,menubar=0,toolbar=0");
	}
	
	function enterkey22() {
        if (window.event.keyCode == 13) {
	// 엔터키가 눌렸을 때 실행할 내용
             checkForm();
        }
	}
</script>
</head>
<body>
	<div class="login">
		<div>
			<form action="LoginServlet" method="post" name="login">
				<p id="ptop"><input type="text" class="logintext" name="userid" placeholder="아이디를 입력하세요">
				<p id="pcenter"><input type="password" class="logintext" name="userpw" placeholder="비밀번호를 입력하세요" onkeyup="enterkey22()">
				<p id="pbottom"><input id="login" type="button" value="로그인" onClick="checkForm()">	
			</form>
		</div>
		<div>
			<input id="find" type="submit" value="id/pw 찾기" onClick="Findidpw()">
			<input id="new" type="submit" value="회원가입" onclick='signUp()'>	
		</div>
	</div>

</body>
</html>