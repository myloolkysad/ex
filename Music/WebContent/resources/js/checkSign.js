function checkSign() {
	  var form = document.signUp;
	  
	  var id = document.signUp.userid.value;
	  var pw = document.signUp.userpw.value;
	  var pw2 = document.signUp.userpw2.value;
	  var name = document.signUp.userName.value;
	  var birth1 = document.signUp.signUp_userBirth1.value;
	  var birth2 = document.signUp.signUp_userBirth2.value;
	  var birth3 = document.signUp.signUp_userBirth3.value;
	  var phone = document.signUp.signUp_userPhone.value;
	  var mail = document.signUp.signUp_userMail;
	  var address = document.signUp.signUp_userAddress1.value;
	  
	  if(id == null || id == "") {
		  alert("아이디를 입력해주세요");
		  document.signUp.userid.focus();
		  document.signUp.userid.select();
		  return false;
	  }
	  
	  if(pw == null || pw == "") {
		  alert("비밀번호를 입력해주세요");
		  document.signUp.userpw.focus();
		  document.signUp.userpw.select();
		  return false;
	  }
	  
	  if(pw2 == null || pw2 == "") {
		  alert("비밀번호 확인을 입력해주세요");
		  document.signUp.userpw2.focus();
		  document.signUp.userpw2.select();
		  return false;
	  }  

	  if(name == null || name == "") {
		  alert("이름을 입력해주세요");
		  document.signUp.userName.focus();
		  document.signUp.userName.select();
		  return false;
	  }
	  
	  if(birth3 == null || birth3 == "") {
		  alert("생년월일을 입력해주세요");
		  document.signUp.signUp_userBirth1.focus();
		  return false;
	  }
	 	 	  
	  if(phone == null || phone == "") {
		  alert("연락처를 입력해주세요");
		  document.signUp.signUp_userPhone.focus();
		  document.signUp.signUp_userPhone.select();
		  return false;
	  }
	  
	  if(mail.value == null || mail.value == "") {
		  alert("메일을 입력해주세요");
		  document.signUp.signUp_userMail.focus();
		  document.signUp.signUp_userMail.select();
		  return false;
	  }
	  
	  if(address == null || address == "") {
		  alert("주소를 입력해주세요");
		  document.signUp.signUp_userAddress1.focus();
		  document.signUp.signUp_userAddress1.select();
		  return false;
	  }
	  
	  if(pw != pw2) {
		  alert("비밀번호가 서로 다릅니다.");
		  document.signUp.signUp_pw2.focus();
		  document.signUp.signUp_pw2.select();
		  return false;
	  }
	  
	  if(isNaN(phone)) {
		  alert("연락처는 숫자만 입력 가능합니다");
		  document.signUp.signUp_userPhone.focus();
		  document.signUp.signUp_userPhone.select();
		  return false;
	  }
	  
	  var mailRe = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	  	  
	  function check(re, what, message) {
	       if(re.test(what.value)) {
	           return true;
	       }
	       alert(message);
	       what.select();
	       return false;
	  }

	  if(!check(mailRe, mail, "적합하지 않은 이메일 형식입니다.")) {  
		  return false;
      }
	  
	  //아이디 중복 유무 확인을 하지 않은 경우
	  if(form.idCheckResult.value=="0") {
	  	alert("아이디 중복확인을 반드시 해주세요.");
	  	return false;
	  }
	  
	  //이메일 인증을 하지 않은 경우
	  if(form.mailCheckResult.value=="0" || form.mailCheckResult.value=="1") {
		  	alert("이메일 인증을 해주세요.");
		  	return false;
	  }

	  document.signUp.submit();
  }

  
  function setDay() {
	  form = document.signUp; //form의 이름을 변수로 만들기(작업하기 편하기 위해서)
	  var year = form.signUp_userBirth1.value.toString(); //입력받은 연도 값 받아오기
	  var month = form.signUp_userBirth2.value.toString(); //입력받은 월 값 받아오기
	  var lastDay = (new Date(year,month,0)).getDate(); //입력받은 연도와 월의 마지막 날을 구하기
	  
	  form.signUp_userBirth3.length = lastDay;  //마지막날만큼 option 갯수 생성
		for(var i=1; i<=lastDay; i++){  //총날짜수만큼 반복문
			
			if(i<10) {   // 1을 01로 만들어주기
				form.signUp_userBirth3.options[i-1] = new Option(i);  //옵션을 생성
				form.signUp_userBirth3.options[i-1].value = "0"+(i);
			}
			else {
				form.signUp_userBirth3.options[i-1] = new Option(i);
				form.signUp_userBirth3.options[i-1].value = i;
			}
		}
	}

  
  function searchAddress() {
      new daum.Postcode({
          oncomplete: function(data) {
              // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
              var addr = ""; // 도로명 주소 변수
              var extraAddr = ""; // 참고항목 변수

             
            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
              if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                  addr = data.roadAddress;
              } else { // 사용자가 지번 주소를 선택했을 경우(J)
                  addr = data.jibunAddress;
              }
              
				
           // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
              if(data.userSelectedType === 'R'){
                  // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                  // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                  if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                      extraAddr += data.bname;
                  }
                  // 건물명이 있고, 공동주택일 경우 추가한다.
                  if(data.buildingName !== '' && data.apartment === 'Y'){
                      extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                  }
                  // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                  if(extraAddr !== ''){
                      extraAddr = ' (' + extraAddr + ')';
                  }
                  // 조합된 참고항목을 해당 필드에 넣는다.
                  document.getElementById("sample6_extraAddress").value = extraAddr;
              
              } else {
                  document.getElementById("sample6_extraAddress").value = '';
              }
           
              // 우편번호와 주소 정보를 해당 필드에 넣는다.
              document.getElementById("sample6_postcode").value = data.zonecode; //5자리 새우편번호 사용
              document.getElementById("sample6_address").value = addr;
              document.getElementById("sample6_detailAddress").focus();
          }
      }).open();
}

//아이디 중복 유무 검사 함수
  function idCheck(form) {
  	//아이디 중복 유무 검사 및 결과를 출력하는 팝업창 생성
  	var url="idCheck.jsp?id="+document.signUp.userid.value;
  	window.open(url, "아이디중복확인", "width=300,height=200,left=450,top=200");
  }
  

//아이디 입력태그에서 키보드를 누른 경우 호출되는 함수
//=> 아이디 입력값이 변경되었으므로 idCheckResult 폼변수의 값을 0으로 초기화
function idCheckInit(form) {
	if(form.idCheckResult.value=="1") {
		form.idCheckResult.value="0";
	}
}

//이메일 입력태그에서 키보드를 누른 경우 호출되는 함수
//=> 이메일 입력값이 변경되었으므로 idCheckResult 폼변수의 값을 0으로 초기화
function mailCheckInit(form) {
	if(form.mailCheckResult.value=="2") {
		form.mailCheckResult.value="0";
	}
}








