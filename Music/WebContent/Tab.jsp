<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

.tabmain .tabs {
	width: 700px; height: 45px;
    left: 30px;
	position: relative;
	margin-left:-3px;
}

.tabmain .tabs li {
	float: left;
	display: block;
	width: 230px;
}

.tabmain .tabs li h3 {
 	background: url("./resources/img/brickwrite.png");
	position: relative;
	width: calc(100% - 10px);
	border-bottom: 1px solid url("./resources/img/tablist_background.png");
	top: 0;
	left: 0;
	padding: 12px 0;
	transition: all 0.3s ease 0s;
	border-radius: 20px;
}

.tabmain .tabs li h3:hover {
	background: url("./resources/img/brickdark.png");
	border-radius: 20px 20px 0px 0px;
	width: calc(98%);
	padding-bottom:13px;
}

.tabmain .tabs li h3 a {
	display: block;
	color: #fff;
	font-size: 16px;
	text-align: center;
}
.tabmain .tabs li h3 a:hover {
	display: block;
	color: #fff;
	font-size: 16px;
	font-weight:800;
	text-align: center;
}

h3 a {
	text-decoration: none;
	font-size: 15px;
}

#link {
	color: whitesmoke;
	text-shadow: 3px 3px 3px white, 25 25 25px gray, 5 5 5px darkgray;
}
</style>
<script type="text/javascript" src="./resources/js/tap.js"></script>
</head>
<body>
	<div class="tabmain">
		<ul class="tabs">
			<li class="m3"><h3 id="a1">
					<a href="javascript:showTabMenu(1)">앨범</a>
				</h3></li>

			<li class="m2"><h3 id="a2">
					<a href="javascript:showTabMenu(2)">인기차트</a>
				</h3></li>

			<li class="m1"><h3 id="a3">
					<a href="javascript:showTabMenu(3)">최신음악</a>
			</h3></li>
		</ul>
	</div>
	<div>
		<div class="contents" id="con1"><jsp:include page="Tab_Album.jsp"/></div>
		<div class="contents" id="con2" style="display: none"><jsp:include page="Tab_Popular.jsp"/></div>
		<div class="contents" id="con3" style="display: none"><jsp:include page="Tab_Recent.jsp"/></div>
	</div>
</body>
</html>