<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:commonLayout title="Modify" tab="mypage">
	<jsp:body>
		<form id = "modify-form">
			USERNAME : 
			<input type = "text" value="${dto.username }" readonly> <br/>
			
			NICKNAME : 
			<input type = "text" value="${dto.nickname }" name="nickname"><br/>
			
			PASSWORD : 
			<input type = "password" value="${dto.password }" name="password"><br/>
			
			TYPE : 
			<input type = "checkbox" ${dto.type == 0 ? 'checked' : ''} 
					name="type" value="0">관리자
					
			<input type = "checkbox" ${dto.type == 1 ? 'checked' : ''} 
					name="type" value="1">일반회원
			
			<button type="button" id="submit-btn">수정</button>
		</form>
		<script>
			$(document).ready(function(){
				$("#submit-btn").click(function(){
					$.post(
						"/myhome/member/modifyAction", 
						$("#modify-form").serialize(), // 서버에게 보낼 데이터(문자열, json)
						function (data){ // response 날아오면 할 일 (data 매개변수 : 서버로부터 받은 데이터)
							console.log(data);
							if(data['result']){
								alert("수정 완료!");
								$("#current-user").text(data['new_nickname']);
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



