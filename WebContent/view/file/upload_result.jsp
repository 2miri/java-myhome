<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:commonLayout>
<jsp:body>
	<c:if test="${not empty originName1 }">
	<div>
		<ul>
			<li> 원본 이름 : ${ originName1 } </li>
			<li> 새 이름 : ${realName1 } </li>
			<li> 크기 : ${fileSize1 }byte </li>
		</ul>
	</div>
	</c:if>
	
	<c:if test="${not empty originName2 }">
	<div>
		<ul>
			<li> 원본 이름 : ${ originName2 } </li>
			<li> 새 이름 : ${realName2 } </li>
			<li> 크기 : ${fileSize2 }byte </li>
		</ul>
	</div>
	</c:if>
	
</jsp:body>
</t:commonLayout>