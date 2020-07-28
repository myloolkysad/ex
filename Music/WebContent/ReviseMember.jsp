<%@page import="java.util.StringTokenizer"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="./resources/img/favicon.png">
<link rel="stylesheet" href="./resources/css/revise.css">
<script type="text/javascript" src="./resources/js/searchAddress.js"></script>
<script type="text/javascript" src="./resources/js/checkSign.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function updateCheck() {
		var pw1 = document.MemberUpdate.memberpw.value;
		var pw2 = document.MemberUpdate.userpw.value;
		var pw3 = document.MemberUpdate.updateMember_userpw.value;
		var phone = document.MemberUpdate.updateMember_userPhone.value;
		var mail = document.MemberUpdate.updateMember_userMail.value;
		var address = document.MemberUpdate.updateMember_userAddress1.value;
		
		if(pw1!=pw2) {
			alert("현재 비밀번호가 일치하지 않습니다.");
			document.MemberUpdate.userpw.select();
			return false;
		}
		
		if(pw2 == "" || phone=="" || mail=="" || address==""){
			alert("모든 항목을 입력해주세요.");
			return false;
		}
		
		if(pw3 == "") {
			var con = confirm("새로운 비밀번호를 입력하지 않으셨습니다. 기존의 비밀번호를 계속 사용하시겠습니까?");
			if(con) {
				document.MemberUpdate.updateMember_userpw.value = document.MemberUpdate.userpw.value;
			} else {
				alert("새로운 비밀번호를 입력해주세요.");
				document.MemberUpdate.updateMember_userpw.focus();
				return false;
			}
		}
		
		
		document.MemberUpdate.submit();
	}
</script>
<title>회원수정</title>
</head>
<body>

	<%
		MemberDto member = (MemberDto) session.getAttribute("member");
		String memberpw = member.getMemberpw(); //기존 비밀번호
		String memberPhone = member.getMemberPhone(); //기존 연락처
		String memberMail = member.getMemberMail(); //기존 메일
		String memberAddress = member.getMemberAddress(); //기존 주소
		
		StringTokenizer address = new StringTokenizer(memberAddress,".");
	%>
	<form action="MemberUpdateServlet" method="post" name="MemberUpdate">
	<div id="revise">
		<p id="title">회원정보 수정</p>
		<div id="left">
			<p>현재 비밀번호</p>
			<p>새 비밀번호</p>
			<p>새 연락처</p>
			<p>새 이메일</p>
			<p>주소</p>
		</div>
		<div id="right">
			<p class="ptext">
				<input type="password" class="text" placeholder="현재 비밀번호 입력" name="userpw">
			</p>
				<input type="hidden" name="memberpw" value="<%=memberpw%>">
			<p class="ptext">
				<input type="password" class="text" placeholder="새 비밀번호 입력" name="updateMember_userpw">
			</p>

			<p class="ptext">
				<input class="text" type="text" placeholder="-제외 입력" maxlength="11" value="<%=memberPhone%>"
				name="updateMember_userPhone">
			</p>

			<p class="ptext">
				<input class="text" type="text" placeholder="선택입력" value="<%=memberMail%>" name="updateMember_userMail">
			</p>
			<% 
			for(int i=0; i<address.countTokens();i++) {
			%>
			<p class="ptext">
				<input class="text" type="text" id="sample6_postcode" placeholder="우편번호" onClick="searchAddress()"
				name="updateMember_userZipcode" value="<%=address.nextToken().substring(1, 6)%>">
				<input class="button" type="button" value="우편번호 찾기" onClick="searchAddress()">
			</p>
			<p class="ptext">
				<input class="text" type="text" id="sample6_address" placeholder="주소" onClick="searchAddress()"
				name="updateMember_userAddress1" value="<%=address.nextToken()%>">
				<input type="text" class="text" id="sample6_detailAddress" placeholder="상세주소" 
				name="updateMember_userAddress2" value="<%=address.nextToken()%>">
			</p>
			<%	
			}
			%>
			<p class="ptext">
				<input type="text" class="text" id="sample6_extraAddress" placeholder="참고항목" onClick="searchAddress()">
			</p>
			<p>
				<input class="revise_button" type="button" value="수정하기" onclick="updateCheck();" >
			</p>
		</div>
	</div>
	</form>
</body>
</html>