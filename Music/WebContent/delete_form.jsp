<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 삭제 암호 묻는 페이지</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
 	 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script type="text/javascript">
function check(){

var str = document.getElementById('check');

 if( str.value == '' || str.value == null ){
    alert( '작성자를 입력해주세요' );
    return false;
}

var blank_pattern = /^\s+|\s+$/g;
if( str.value.replace( blank_pattern, '' ) == "" ){
    alert('공백만 입력되었습니다 ');
    return false;
}

 

//공백 금지
//var blank_pattern = /^\s+|\s+$/g;(/\s/g
var blank_pattern = /[\s]/g;
if( blank_pattern.test( str.value) == true){
    alert(' 공백은 사용할 수 없습니다. ');
    return false;
}


var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

if( special_pattern.test(str.value) == true ){
    alert('특수문자는 사용할 수 없습니다.');
    return false;
}

alert( '최종 : ' + str.value );

/*
if( str.value.search(/\W|\s/g) > -1 ){
    alert( '특수문자 또는 공백을 입력할 수 없습니다.' );
    str.focus();
    return false;
}*/

}
</script>

</head>
<body>

	<form id="check" action="delete.jsp" method="post">
		<input type="hidden" name="articleId" value="${param.articleId }">
		글 암호 :<input id="disabledTextInput" class="form-control" type="password" name="password" placeholder="password" onsubmit="return check()" required="required"/><Br>
		 <input class="btn btn-primary" type="submit" value="삭제">
	
	</form>
	</body>

</html>
