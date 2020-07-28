<%@page import="java.text.DateFormat"%>
<%@page import="dto.MemberDto"%>
<%@page import ="java.util.Date"%>
<%@page import = "java.text.SimpleDateFormat"%>
<%@page import = "java.text.ParseException" %>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="./resources/img/favicon.png">
<link rel="stylesheet" href="./resources/css/mypage.css">
<link rel="stylesheet" href="./resources/css/main.css">
<script type="text/javascript" src="./resources/js/revise.js"></script>
<title>마이페이지</title>
</head>
<body>
	<div class="backA">
		<div class="subFrame1">
			<div class="topmypage">
				<div class="LogoFrame">
					<a href="LoginMember.jsp"> <img width="95" height="90"
						src="./resources/img/favicon.png" alt="logo"></a>
				</div>
				<div id="theme">마이페이지</div>
				<div id="logoutFrame">
					<form action="LogoutServlet" method="post">
						<p><input id="logout" type="submit" value="로그아웃">
					</form>
				</div>
			</div>
			<div class="middlepage">
				<div id="ticket">
					<div class="ticketTheme">
						<label id="text1">내 정보</label>
					</div>
					<%
					MemberDto member = (MemberDto) session.getAttribute("member");
					String id = member.getMemberid();
					String qw = member.getMemberFreeTicket();
					
					Date today = new Date();
					SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
					String now = format.format(today);
					String rating = null;
					try{
						Date nowday = format.parse(now);
						Date ticketDay = format.parse(qw);
						long calDate = nowday.getTime() - ticketDay.getTime();
						long calDateDays = calDate / (24*60*60*1000);
						calDateDays = Math.abs(calDateDays);
						System.out.println("두 날짜의 날짜 차이: "+calDateDays);
						if(calDateDays>=3650){
							rating ="space";
						}else if(calDateDays>=365 && calDateDays<3650){
							rating ="sun";
						}else if(calDateDays>=30 && calDateDays<365){
							rating = "moon";
						}else if(calDateDays>=1 && calDateDays<30){
							rating = "star";
						}else{
							rating = "none";
						}
					}catch(ParseException e){
						e.printStackTrace();
					}
					%>
					<div class="ticket1">
						<label>회원 등급</label>
						<img alt="등급이 표시되지 않습니다." src="./resources/img/rating/<%=rating %>.png">
					</div>
					<div class="ticket2">
						이용권 만료일 : <span> <%=qw %> </span>
					</div>
				</div>
				<div id="purchase">
					<div id="purchaseText">이용권 구매</div>
                     <form action="BuyTicket" method="post" name="ticket">
					<div id="purchaseRadio">
						<label class="purchaseinput">
							<input type="radio" name="item" value="1"><span>1달권</span></label>
						<label class="purchaseinput">
							<input type="radio" name="item"value="2"><span>6달권</span></label>
						<label class="purchaseinput">
							<input type="radio" name="item" value="3"><span>1년권</span></label>
					</div>
					<input id="buy" type="submit" value="구매">
                    </form>
                    
				</div>
			</div>
			
			<div class="bottompage">
			<a href="LoginMember.jsp"><input id="home" type="button" value="홈으로"></a>
				<form action="#" method="post" name="MemberUpdate">
					<input id="revise" type="submit" value="회원정보수정" onclick="re()">
				</form>
				<form action="WithdrawServlet" method="post" name="Withdraw">
					<input id="dropout" type="submit" value="회원탈퇴">
				</form>
			</div>
		</div>
	</div>
</body>
</html>