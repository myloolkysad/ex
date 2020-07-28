<%@page import="Email.sendMailServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%	
	String mail = request.getParameter("mail");
	System.out.println(mail);
	sendMailServlet send = new sendMailServlet();
	String code = send.sendMail(mail);
%>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
	<script type="text/javascript">
	function checkCode(){
		  var v1 = form2.code_check.value;
		  var v2 = form2.code.value;
		  if(v1!=v2){
			   document.getElementById('checkCode').style.color = "red";
			   document.getElementById('checkCode').innerHTML = "잘못된인증번호";
               makeNull();
		  }else{
			   document.getElementById('checkCode').style.color = "blue";
			   document.getElementById('checkCode').innerHTML = "인증되었습니다."; 
			   makeReal();
		  }
		 }
	
	function makeReal(){
		var hi = document.getElementById("hi");
		hi.type="submit";
	}
    function makeNull(){
		var hi = document.getElementById("hi");
		hi.type="hidden";
	}
    
	function okCheck() {
		//부모창(opener내장객체:새롭게 열린창의 이름)에 값을 전달하여 출력 또는 저장
		opener.document.signUp.signUp_cerNum.value="<%=code%>";
		opener.document.signUp.codeNumber.value="<%=code%>";
		opener.document.signUp.mailCheckResult.value="2";
		opener.document.signUp.signUp_cerNum.focus();
		opener.document.signUp.signUp_cerNum.select();
		//현재 창 종료
		window.close();
	}
    
</script>
</head>
<body>
	<form id = "form2" action="javascript:getPassword()">
		<table>
			<tr>
				<td><span>인증번호</span></td>
				<td><input type="text" name="code" id="code" onkeyup="checkCode()" placeholder="인증번호를 입력하세요." />
					<div id="checkCode"></div></td>
				<td><input type="hidden" readonly="readonly" name="code_check"	id="code_check" value="<%=code%>" ></td>
			</tr>
		</table>
		<input id = "hi" type="hidden" value='인증하기' onclick="okCheck();">
		</form>
	
</body>
</html>