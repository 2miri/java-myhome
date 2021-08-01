<%@page import="myhome.domain.MemberDao"%>
<%@page import="myhome.domain.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:commonLayout title="Join" tab="join">
	<jsp:body>
	<c:choose>
		<c:when test="${result }">
			<h1>회원가입에 감사드립니다.</h1>
			<button onclick="location.href='login.jsp'" class="btn btn-outline-light">로그인 하러가기</button>
		</c:when>
		<c:otherwise>
			<h1>회원가입에 실패하였습니다.</h1>
			<button onclick="history.back()">뒤로가기</button>
		</c:otherwise>
	</c:choose>
	</jsp:body>
</t:commonLayout>





