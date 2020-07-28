<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//MemberDto member = (MemberDto) session.getAttribute("member");	
%>
<script type="text/javascript">
	function exec(){
		window.close();	
	}
</script>
회원가입이 완료되었습니다.
<input type="button" value="확인" onclick="exec()">
<%--
<%=member.getMemberid() %><br>
<%=member.getMemberpw() %><br>
<%=member.getMemberName() %><br>
<%=member.getMemberBirth() %><br>
<%=member.getMemberPhone() %><br>
<%=member.getMemberMail() %><br>
<%=member.getMemberAddress() %><br>
<%=member.getMemberSignUpDay() %><br>
<%=member.getMemberFinalLogin() %><br>
<%=member.getMemberFreeTicket() %><br>
 --%>