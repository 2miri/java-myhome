<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:commonLayout title="Join" tab="join">
<jsp:body>

<div class="card mx-auto border-light bg-dark" style="width: 28rem;">
<p class="card-header display-5 lead text-center">회원가입</p>
<div class="card-body">
	
	<form class="cart-text" id="join-form" action="join_logic"
			method="post">
			
	<!-- username 입력 받기 -->
	<div class="form-group">
		<label for="username">아이디</label>
		<input type="text" class="form-control" id="username"
					name="username" aria-describedby="username-help">
		<small id="username-help" class="form-text text-muted">
			로그인 시 사용할 사용자 이름을 입력해주세요.
		</small>
	</div>
	
	<!-- password 2번 입력 받기 -->
	<div class="form-group">
		<label for="password">패스워드</label>
		<input type="password" class="form-control" id="password"
					name="password" aria-describedby="password-help">
		<small id="password-help" class="form-text text-muted">
			로그인 시 사용할 사용자 이름을 입력해주세요.
		</small>
		<input type="password" class="form-control" id="re-password"
					aria-describedby="re-password-help">
		<small id="re-password-help" class="form-text text-muted">
			패스워드를 한 번 더 입력하세요.
		</small>
	</div>
	
	<!-- nickname 입력 받기 -->
	<div class="form-group">
		<label for="nickname">닉네임</label>
		<input type="text" class="form-control" id="nickname"
					name="nickname" aria-describedby="nickname-help">
		<small id="nickname-help" class="form-text text-muted">
			다른 사용자에게 보여줄 당신의 닉네임을 입력해주세요.
		</small>
	</div>
	
	<!-- 회원 타입 입력 받기 -->
	<div class="form-check form-check-inline">
		<input type="radio" class="form-check-input" name="type" value="1"
					id="type-general" checked>
		 <label for="type-general">일반회원</label>
	</div>
	<div class="form-check form-check-inline">
		<input type="radio" class="form-check-input" name="type" value="0"
					id="type-admin">
		 <label for="type-admin">관리자</label>
	</div>
	
	<hr class="bg-light" />
	
	<button type="button" onclick="check_validation()"
				class="btn btn-outline-light">가입하기</button>
	<!-- type="button" 을 하는 이유?
		<button>이 <form>안에 들어있으면 기본 적용되는 것이 
		type="submit" 임.
		그냥 냅두면 클릭했을 때 submit이 실행되고 onclick 실행 안됨.
	  -->
	</form>
	</div>
</div>

<script>
	function check_validation(){
		
		// form DOM 객체 얻어옴
		var form = document.getElementById("join-form");
		
		// form의 패스워드 값 2개 얻어옴
		var pw1 = form.elements["password"].value;
		var pw2 = form.elements["re-password"].value;
		
		if(pw1 == ""){
			alert('패스워드를 입력하세요.');
			return;
		}
		if(pw1 != pw2){
			alert('두 패스워드가 일치하지 않습니다.');
			return;
		}
		
		form.submit();
	}
</script>


</jsp:body>
</t:commonLayout>




