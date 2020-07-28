<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 1L);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
 	 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 목록</title>

<style>
table {
	text-align: center;
}

.seq {
	font-size: 20px;
	color: red;
}
.title_class{
	font-size: 20px;
	color: Blue;
}
.title_name{
	font-size : 20px;
	font-weight: bold;;
}

.title {
	text-align: left;
	font-size: 20px;
	color: Blue;
	font-weight: bold;
}
</style>

</head>
<body>

<div class="card">
  <div class="card-body">
		<c:if test="${articlePage.totalPageCount > 0 }">
			<tr>
				<td colspan="8">${articlePage.startRow} - ${articlePage.endRow}
					[${articlePage.requestPage}/${articlePage.totalPageCount}]</td>
			</tr>
		</c:if>
		  </div>
</div>
	<table  class="table" id="wrap" border="1" >
	    <thead class="thead-dark">
		<tr>
			<th>글번호</th>
			<th class="title_class">제목</th>
			<th class="title_name">작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>groupNum</th>
			<th>seqNum</th>
			<th>GroupLevel</th>
		</tr>
		</thead>
		<c:choose>
			<c:when test="${articlePage.hasArticle == false }">

				<tr>
					<td colspan="8">게시글이 없습니다.</td>
				</tr>
			</c:when>

			<c:otherwise>

				<c:forEach var="article" items="${articlePage.articleList}">
					<tr>
						<td>${article.id}</td>
						<td class="title"><c:if test="${article.lv > 0 }">
								<c:forEach begin="1" end="${article.lv}">-</c:forEach>&gt;
							</c:if> 
								
									<c:set var="query"
										value="articleId=${article.id}&page=${articlePage.requestPage}" />
									<a href="<c:url value="/read.jsp?${query}"/>">
										${article.title } </a>
								
								
								<!--
								<c:choose> 
								<c:when test="${article.deleted==false}"> 
								</c:when>
								
								<c:otherwise>
									원본글이 삭제되었습니다. 
								</c:otherwise>
								</c:choose>
								-->
								
							
						<td class="title_name">${article.writerName}</td>
						<td>${article.postingDate}</td>
						<td>${article.readCount}</td>

						<td>${article.grp}
						<td class="seq"><b>${article.seq}</b></td>
						<td><b>${ article.lv} </b></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="8"><c:if test="${articlePage.beginPage > 10 }">
							<a
								href="<c:url value="/list.jsp?page=${articlePage.beginPage-1}" />">이전</a>
						</c:if> <c:forEach var="pno" begin="${articlePage.beginPage}"
							end="${articlePage.endPage}">
							<a href="<c:url value="/list.jsp?page=${pno}" />">[${pno}]</a>
						</c:forEach> <c:if test="${articlePage.endPage < articlePage.totalPageCount}">
							<a
								href="<c:url value="/list.jsp?page=${articlePage.endPage+1}" />">다음</a>
						</c:if></td>
				</tr>
			</c:otherwise>
		</c:choose>
	
		<tr>
			<td colspan="8"><a href="writeForm.jsp"><strong>글쓰기</strong></a> ,&nbsp; &nbsp;&nbsp; 시작페이지
				${articlePage.beginPage}   &nbsp;&nbsp;&nbsp;마지막페이지 ${articlePage.endPage}  &nbsp; &nbsp; &nbsp;다음 페이지
				${articlePage.totalPageCount }</td>
		</tr>
	
	</table>
</body>
</html>