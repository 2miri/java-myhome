<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:commonLayout>
	<jsp:body>
		<h1 class="display-4 p-2 lead">새 글</h1>
		<div class="border rounded border-light p-4">
		
		<form action="${pageContext.request.contextPath }/board/write"
					method="post">
		
		<label for="title">제목</label>
		<div class="input-group mb-2 mr-sm-2">
		  <div class="input-group-prepend">
		    <div class="input-group-text">제목</div>
		  </div>
		  <input type="text" class="form-control" id="title" name="title"
							placeholder="제목을 입력하세요.">
		</div>
		
		<hr class="bg-light m-2" />
		
		<div class="form-group">
			<label for="content">글 내용</label>
			<textarea class="form-control" id="content" name="content" rows="20"
							placeholder="본문을 입력하세요."></textarea>
		</div>
		<button type="submit" class="btn btn-light">저장</button>
		</form>
		</div>
	</jsp:body>
</t:commonLayout>