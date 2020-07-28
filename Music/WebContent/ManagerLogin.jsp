<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./resources/css/logout.css">
<style type="text/css">
	b{
		font-size: 25px;
	}
</style>
<script type="text/javascript">
function manager() {
	var signUp_open = window.open("/MusicWeb/ManagerWindow.jsp", "PopupWin", "width=1005,height=805");
}
</script>
</head>
<body>
	<div class="logout">
		<div>
			<p id="idtext">
				<b>관리자</b><br>로그인 되었습니다.
			<form action="LogoutServlet" method="post">
				<p><input id="logout" type="submit" value="로그아웃">
			</form>
		</div>
		<div>
			<p><input id="mypage" type="submit" value="관리창" onclick="manager()">
		</div>
	</div>
</body>
</html>