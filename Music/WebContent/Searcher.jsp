<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<link rel="stylesheet" href="./resources/css/search.css">
<link rel="stylesheet" href="./resources/css/coordinate.css">

<script type="text/javascript">
    function search_button(){
    	var url = "SearchWindow.jsp?what="+document.getElementById('search_text').value;
    	var searcher_open = window.open(url, "search", "width=710,height=810");
    }
    
	function enterkey() {
        if (window.event.keyCode == 13) {
		// 엔터키가 눌렸을 때 실행할 내용
             search_button();
        }
	}
</script>
</head>
<body>
    <div id="search">
        <input id="search_text" class="searchText" type="search" placeholder="검색" onkeyup="enterkey();">
        <input id="search_button" class="searchBt" type="button" value="검색" onclick='search_button()' >
    </div>
</body>
</html>