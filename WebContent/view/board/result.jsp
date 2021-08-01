<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:commonLayout>
	<jsp:body>
		<h2 class="display-5">
			<c:choose>
				<c:when test="${status eq 'write' }">글쓰기 ${result ? '성공' : '실패' }</c:when>
				<c:when test="${status eq 'delete' }">글삭제 ${result ? '성공' : '실패' }</c:when>
				<c:when test="${status eq 'modify' }">글성공 ${result ? '성공' : '실패' }</c:when>
			</c:choose>
		</h2>
		<a class="btn btn-light" href="${pageContext.request.contextPath }/board/list">목록으로</a>
	</jsp:body>
</t:commonLayout>