<%@ page language="java" contentType="text/html; charset=utf-8"%>

<html>
<head>
<style type="text/css">
    #aaa{
        position: absolute;
        top: 30px;
        width:270px; height: 70px;
		background: url("./resources/img/brickwrite.png"),rgba(211,224,238,0.4);
        text-align: center;
        cursor: pointer;
        border-radius: 80px;
    }
    #aaa img{
    	left: 50px;
    }
</style>
<script type="text/javascript">
	function bulletin() {
		var bulletin_open = window.open("/MusicWeb/list.jsp", "bulletin", "width=800,height=900");
	}
</script>
</head>
<body>
	<a id="aaa" onclick="bulletin()">
	<img style="float:right" width="40px" height="40px" src="./resources/img/scope.png" alt="scope" >
	<br>&emsp;&emsp;게시판</a>
</body>
</html>