<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기 입력화면</title>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />

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

	<form id="check" action="write.jsp" method="post">
		제목 : &nbsp; &nbsp; <input type="text" name="title" size="20" ><br>
		작성자 : <input type="text" name="writerName"  required="required"/><br>
		글암호 : <input type="password" name="password" onsubmit="return check()" required="required"/> <br>
		글 내용 : <br>
		<textarea rows="5" cols="40" name="content" placeholder="내용을 기입하세요"> </textarea>
		<br><input class="btn btn-success" type="submit" value="전송"/>
	
	</form>


</body>
</html>