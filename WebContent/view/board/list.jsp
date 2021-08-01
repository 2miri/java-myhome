<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:commonLayout>
	<jsp:body>
	<style>
		
		.pagination > li > a, 
		.pagination > li > span {
		  background-color: #2c3e50;
		  border: 1px solid #2c3e50;
		  border-radius: 4px;
		  color: #fff;
		  float: left;
		  font-size: 14px;
		  line-height: 1.42857;
		  margin-right: 5px;
		  padding: 8px 15px;
		  position: relative;
		  text-decoration: none;
		}
		.pagination > li > a.active,
		.pagination > li > a:hover,
		.pagination > li > span:hover,
		.pagination > li > a:focus,
		.pagination > li > span:focus {
		  background-color: #34495e !important;
		  border-color: #2c3e50;
		  color: #fff;
		}
	
	</style>
	
		<c:if test = "${not empty currentDto }">
			<a class = "btn btn-light col-2 mb-3" href="${pageContext.request.contextPath }/board/write">글쓰기</a>
		</c:if>
		
		<table class="table table-hover table-dark">
			<thead>
				<tr>
					<th scope="col">NO</th>
					<th scope="col" colspan ="4">TITLE</th>
					<th scope="col" colspan = "2">TIME</th>
					<th scope="col">HIT</th>
					<th scope="col" colspan="2">WRITER</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list }">
				<tr onclick="location.href='${pageContext.request.contextPath }/board/read?no=${dto.no }'">
					<th scope="row">${dto.no }</th>
					<td scope="col" colspan ="4">${dto.title }</td>
					<td scope="col" colspan = "2">${dto.regdate }</td>
					<td scope="col">${dto.hit }</td>
					<td scope="col" colspan="2">${dto.writerNickname }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="pagination justify-content-center">
		
		<c:if test="${param.page - 3 gt 0}">
			<li class = "page-item"><a class="page-link" href="${pageContext.request.contextPath }/board/list?page=${param.page-3 }">이전</a></li>
		</c:if>
		
		<c:if test="${param.page - 2 gt 0}">
			<li class = "page-item"><a class="page-link" href="${pageContext.request.contextPath }/board/list?page=${param.page-2 }">${param.page-2 }</a></li>
		</c:if>
		
		<c:if test="${param.page - 1 gt 0}">
			<li class = "page-item"><a class="page-link" href="${pageContext.request.contextPath }/board/list?page=${param.page-1 }">${param.page-1 }</a></li>
		</c:if>
			<li class = "page-item"><a class="page-link active" href="#">${param.page }</a></li>

		<c:if test="${param.page + 1 le lastPage}">
			<li class = "page-item"><a class="page-link" href="${pageContext.request.contextPath }/board/list?page=${param.page+1 }">${param.page+1 }</a></li>
		</c:if>

		<c:if test="${param.page + 2 le lastPage}">
			<li class = "page-item"><a class="page-link" href="${pageContext.request.contextPath }/board/list?page=${param.page+2 }">${param.page+2 }</a></li>
		</c:if>
		
		<c:if test="${param.page + 3 le lastPage}">
			<li class = "page-item"><a class="page-link" href="${pageContext.request.contextPath }/board/list?page=${param.page+3 }">다음</a></li>
		</c:if>
		</ul>
		
	</jsp:body>
</t:commonLayout>







