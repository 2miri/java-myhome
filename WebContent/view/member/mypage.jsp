<%@page import="myhome.domain.MemberDto"%>
<%@page import="myhome.domain.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:commonLayout title="My Page" tab="mypage">
	<jsp:body>
	<c:choose>
		<c:when test="${not empty currentDto}">
			<div class="d-flex justify-content-center">
			<a class="btn btn-danger justify-content-center" href="${pageContext.request.contextPath }/view/member/delete?no=${currentDto.no }">회원탈퇴</a>
			<a class="btn btn-warning justify-content-center" href="${pageContext.request.contextPath }/view/member/modify?no=${currentDto.no }">회원수정</a>
			</div>
			
			<div>
				USERNAME : ${dto.username } <br/>
				NICKNAME : ${dto.nickname } <br/>
				${dto.type == 0 ? '관리자':'일반회원' }<br/>
				REGDATE : ${dto.regdate } <br/>
				NO : ${dto.no } <br/>
			</div>
			
		</c:when>
		
		<c:otherwise>
			<p class="display-5">로그인을 하셔야 합니다.</p>
		</c:otherwise>
	</c:choose>	
	</jsp:body>
</t:commonLayout>









