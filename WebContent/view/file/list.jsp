<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:commonLayout>
	<jsp:body>
		
		<c:if test="${not empty currentDto }">
		<div>
			<a href="${pageContext.request.contextPath }/file/upload" 
				class="btn btn-outline-light">업로드</a>
		</div>
		</c:if>
		
		<!-- 파일 목록 -->
		<table class="table table-hover table-dark">
			<thead>
				<tr>
					<th scope="col">NO</th>
					<th scope="col" colspan ="3">FILENAME</th>
					<th scope="col">DOWNLOAD</th>
					<th scope="col" colspan="2">UPLOADER</th>
					<th scope="col" colspan = "3">TIME</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list }">
				<tr onclick="location.href='${pageContext.request.contextPath }/file/download?no=${dto.no }'">
					<td scope="col">${dto.no }</td>
					<td scope="col" colspan ="3">${dto.filename }</td>
					<td scope="col">${dto.count }</td>
					<td scope="col" colspan="2">${dto.uploaderNickname }</td>
					<td scope="col" colspan = "3">${dto.regdate }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:body>
</t:commonLayout>