<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<link rel="shortcut icon" href="./resources/img/favicon.png">
<link rel="stylesheet" href="./resources/css/signup.css">
<title>회원가입</title>
</head>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="./resources/js/searchAddress.js"></script>
<script type="text/javascript" src="./resources/js/checkSign.js"></script>
<script type="text/javascript">

function sendMail(form) {
  	//이메일 입력 확인
  	if (form.signUp_userMail.value=="") {
  		alert("이메일 입력 후 인증번호 받기 버튼을 눌러주세요.");
  		form.signUp_userMail.focus();
  		return;
  	}

  	//이메일 인증창 생성
  	var url="checkcode.jsp?mail="+document.signUp.signUp_userMail.value;
  	window.open(url, "인증메일 발송", "width=300,height=200,left=450,top=200");
}

function changeCode() {
	var v = document.signUp.mailCheckResult.value;
	var c = document.signUp.codeNumber.value;
	var c2 = document.signUp.signUp_cerNum.value;
	
	if(v=="0"){
		 document.getElementById("checkMail").style.color = "red";
		 document.getElementById("checkMail").innerHTML = "인증번호를 입력해주세요";
	  }else if(v=="1"){
		 document.getElementById("checkMail").style.color = "red";
		 document.getElementById("checkMail").innerHTML = "잘못된인증번호";
	  }else if(v=="2"){
		 document.getElementById('checkMail').style.color = "blue";
		 document.getElementById('checkMail').innerHTML = "인증되었습니다."; 
	  }
	
	if(c==c2) {
		document.getElementById('checkMail').style.color = "blue";
		document.getElementById('checkMail').innerHTML = "인증되었습니다."; 
	}else if(c!=c2 && c2!="") {
		document.getElementById("checkMail").style.color = "red";
		document.getElementById("checkMail").innerHTML = "잘못된인증번호";
	}else if(c2==null || c2=="") {
		document.getElementById("checkMail").style.color = "red";
		document.getElementById("checkMail").innerHTML = "인증번호를 입력해주세요";
	}
	
}

</script>
<body>
<form action="MemberInsertServlet" name="signUp" method="post">
    <div id="signUp">
        <div id="signUp_left">
            <p class="p_text">ID</p>
            <p class="p_text">비밀번호</p>
            <p class="p_text">비밀번호 확인</p>
            <p class="p_text">이름</p>
            <p class="p_text">생년월일</p>
            <p class="p_text">연락처</p>
            <p class="p_text">E-mail</p>
            <p class="p_text">인증번호</p>
            <p class="p_text">주소</p>
        </div>
        
        <div id="signUp_right">
        <p><input class="signUp_ID" type="text" name="userid" onkeydown="idCheckInit(signUp);">
        
        	
        	<jsp:useBean id="checkid" class="conn.member.MemberSearchServlet"/>
            <input class="signUp_IDCheckButton" type="button" value="중복확인"	name="userid2" id="signUp_id2" onclick="idCheck(signUp);"></p>
        <p><input class="signUp_PW" type="password" name="userpw"></p>
        <p><input class="signUp_PWCK" type="password" name="userpw2"></p>
        <p><input class="signUp_Name" type="text" name="userName"></p>
        
        <% Calendar cal = Calendar.getInstance();
		   cal.setTime(new Date());
	       DateFormat df = new SimpleDateFormat("yyyy");
	       int a = Integer.parseInt((df.format(cal.getTime())));
	    %>
        <p><select class="signUp_Year1" name="signUp_userBirth1" id="signUp_userBirth1">
   	 				   <option value="" selected disabled>년</option>
   	 				   <%for(int i=a; i>=1900; i--){ %>
    	   				<option value="<%=i %>"><%=i %></option>
       				   <%} %>
     			   </select>년&nbsp;
     			    
     			        <% String k = ""; 
     			           String k2 = "";
     			        %>			   
     			   <select class="signUp_Year2" name="signUp_userBirth2" id="signUp_userBirth2" onchange="setDay()" onclick="setDay()">
       					<option value="" selected disabled>월</option>
       					<%for(int i=1; i<=12; i++){
       					if(i<10) { 
       						k = "0"+Integer.toString(i); 
       						k2 = Integer.toString(i);
       					}
       					else {
       						k = Integer.toString(i); 
       						k2 = Integer.toString(i);
       					}
       					
       					%>
       					<option value="<%=k %>"><%=k2 %></option>
       					<%
       					} 
       					%>
     			   </select>월&nbsp;    				
     			   <select class="signUp_Year3" name="signUp_userBirth3" id="signUp_userBirth3">
     			   		<option value="" selected disabled>일</option>
       					<option></option>
     			   </select>일<br>
         </p>
        <p><input class="signUp_Phone" type="text" name="signUp_userPhone" id="signUp_userPhone" placeholder="-제외 입력" maxlength="11"></p>
         
        <p><input class="signUp_Email" type="text" name="signUp_userMail" id="signUp_userMail" placeholder="@포함 전체 입력" onkeydown="mailCheckInit(signUp);">

        <input class="signUp_EmailCheckButton" type="button" value="인증번호받기" onclick="sendMail(signUp);"></p>
        
        <div id="mm"><div class="mm"><input class="signUp_Identify" name="signUp_cerNum" onclick="changeCode();" onkeyup="changeCode();" onfocus="changeCode();"></div> <div class="mm" id="checkMail"></div>  </div>        
         
          
        <p><input type="text" id="sample6_postcode" name="signUp_zipCode" placeholder="우편번호" onclick="searchAddress()">
		<input type="button" onclick="searchAddress()" value="우편번호 찾기">
		<p><input type="text" id="sample6_address" name="signUp_userAddress1" placeholder="주소" onclick="searchAddress()">
		<input type="text" id="sample6_detailAddress" name="signUp_userAddress2" placeholder="상세주소">
		<p><input type="text" id="sample6_extraAddress" placeholder="참고항목" onclick="searchAddress()">
		<%-- 아이디 중복 유무 실행 및 결과를 확인하기 위한 변수[0:미확인(중복), 1:확인(미중복)] --%>
		<input type="hidden" name="idCheckResult" value="0" />
		<%-- 이메일 인증 유무 실행 및 결과를 확인하기 위한 변수[0:아무것도 안한 상태, 1:미인증, 2:인증] --%>
		<input type="hidden" name="mailCheckResult" value="0" />
		<%-- 코드번호를 담는 변수--%>
		<input type="hidden" name="codeNumber" value="-11111" />
        <p><input class="signUp_Button" type="button" value="회원가입하기"	name="signUpBotton" onclick="checkSign()"></p>
    </div>
 </div>
</form>
</body>
</html>