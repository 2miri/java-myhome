<%@page import="myhome.domain.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:commonLayout title="Delete" tab="mypage">
	<jsp:body>
		
	<c:choose>
		<c:when test="${result}">
			<h2 class="display-5">회원삭제를 완료하였습니다.</h2>
			<a class="btn btn-outline-light" 
			href="${pageContext.request.contextPath }/view/member/join.jsp">회원가입으로</a>
		</c:when>
		
		<c:otherwise>
			<h2 class="display-5">회원삭제에 실패하였습니다.</h2>
			<a class="btn btn-outline-light" 
			href="${pageContext.request.contextPath }/view/member/login.jsp">로그인으로</a>
		</c:otherwise>
	</c:choose>
		
	</jsp:body>
</t:commonLayout>






