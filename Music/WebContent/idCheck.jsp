
<%@page import="conn.member.MemberSearchServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
아이디를 전달받아 중복유무를 확인한 후 출력하고 전달하는 페이지
 => 아이디가 중복될 경우 : 아이디 재입력 후 다시 중복유무 검사
 => 아이디가 중복되지 않을 경우 : 아이디와 결과를 부모창으로 전달하여 저장(출력)
--%>
<%
	//전달받은 아이디를 반환받아 저장
	String id=request.getParameter("id");
	
	//아이디를 전달하여 member 테이블에 해당 아이디 존재 유무 검사 : 아이디 중복여부 검사
	// => DAO 클래스 getMember() 메소드 호출
	// => 반환결과 - null : 아이디에 해당하는 회원정보 미존재(아이디 미중복)
	// => 반환결과 - DTO 객체 : 아이디에 해당하는 회원정보 존재(아이디 중복)
	  MemberSearchServlet member= new MemberSearchServlet();
	  boolean check = member.check(id);
%>
<html>
<head>
<title>ID 중복확인</title>

<script type="text/javascript">
	//아이디 중복 유무 검사 함수
	function idCheck(form) {
		//아이디 입력 확인
		if (form.id.value=="") {
			alert("아이디 입력 후 중복확인 버튼을 눌러주세요.");
			form.id.focus();
			return;
		}
		
		//입력 데이터 전달
		form.action="idCheck.jsp";
		form.method="post";
		form.submit();
	}
	
	//부모 창에 존재하는 폼변수 값을 전달하고 현재 창을 종료하는 함수
	function okCheck() {
		//부모창(opener내장객체:새롭게 열린창의 이름)에 값을 전달하여 출력 또는 저장
		opener.document.signUp.userid.value="<%=id%>";
		opener.document.signUp.idCheckResult.value="1";
		//현재 창 종료
		window.close();
	}
</script>
</head>
<body>
	<form name="idCheckForm">
	<table width="100%" height="90%">
		<tr><td>&nbsp;</td></tr>
		<% if(check) { //아이디가 중복될 경우 %>
			<tr>
				<td align="center" class="t1">
					입력한 <font color="red">[<%=id %>]</font>는 이미
					사용중인 아이디입니다.<br><br>
				</td>
			</tr>
			<tr>
				<td align="center" class="bottom">
					<input type="text" name="id" maxlength="13" size="13" class="TXTFLD" />
					<button type="button" onclick="idCheck(idCheckForm)">확인</button>
				</td>
			</tr>
		<% } else { //아이디가 중복되지 않은 경우 %>
			<tr>
				<td align="center" class="t1">
					입력한 <font color="red">[<%=id %>]</font>는 사용
					가능한 아이디입니다.<br><br>
				</td>
			</tr>
			<tr>
				<td align="center" valign="bottom">
					<button type="button" onclick="okCheck();">사용</button>
				</td>
			</tr>
		<% } %>
	</table>
	</form>
</body>
</html>