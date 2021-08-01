<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:commonLayout>
	<jsp:body>
		<h1 class="display-4 p-2 lead">수정 글</h1>
		<div class="border rounded border-light p-4">
		
		<form id ="modify-form">
		
		<input type = "hidden" name = "no" value="${dto.no }">
		
		<label for="title">제목</label>
		<div class="input-group mb-2 mr-sm-2">
		  <div class="input-group-prepend">
		    <div class="input-group-text">제목</div>
		  </div>
		  <input type="text" class="form-control" id="title" name="title"
							value="${dto.title }">
		</div>
		
		<hr class="bg-light m-2" />
		
		<div class="form-group">
			<label for="content">글 내용</label>
			<textarea class="form-control" id="content" name="content" rows="20">${dto.content}</textarea>
		</div>
		<button type = "button" id = "submit-btn" class="btn btn-light">저장</button>
		</form>
		</div>
	
<script>
	$(document).ready(function(){
		$("#submit-btn").click(function(){
			$.post(
				"${pageContext.request.contextPath}/board/modify", 
				$("#modify-form").serialize(), // 서버에게 보낼 데이터(문자열, json)
				function (data){ // response 날아오면 할 일 (data 매개변수 : 서버로부터 받은 데이터)
					console.log(data);
					if(data['result']){
						alert("수정 완료!");
						location.href="${pageContext.request.contextPath}/board/read?no=${dto.no }";
					} else {
						alert("수정 실패..");
					}
				},
				"json" // 서버에게 받은 데이터의 content 타입(나는 json 받을거야!)
			);
		});
	});
</script>
		
		
	</jsp:body>
</t:commonLayout>