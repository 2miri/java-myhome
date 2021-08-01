<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:commonLayout title="Login" tab="login">
	
	<jsp:body>
	<div class="card mx-auto border-light bg-dark" style="width: 28rem;">
		<p class="card-header display-5 lead text-center">로그인</p>
		<div class="card-body">
			<form class="cart-text" id="login-form" action="login_logic.do"
					method="post"> 
					
			<div class="form-group">
				<input type="text" class="form-control needs-validation"
							id="username" name="username" placeholder="Username" value="${cookie.rememberId.value }">
							
				<div id= "alert-username" class="invalid-feedback" style="display:none;" role="alert">아이디를 입력하세요.</div>
			</div>
			<div class="form-group">
				<input type="password" class="form-control needs-validation"
							id="password" name="password" placeholder="Password" autocomplete="current-password">
				<div id= "alert-password"  class="invalid-feedback" style="display:none;" role="alert">패스워드를 입력하세요.</div>
			</div>
			
			<div class="form-check form-group">
			  <input class="form-check-input" type="checkbox" 
			  		 id="remember-me" name="remember_me" checked>
			  <label class="form-check-label" for="remember-me">
			    아이디 기억하기
			  </label>
			</div>
			
			
			<button id = "submit-btn" class="btn btn-outline-light" type="button">로그인</button>
			</form>
			</div>
		</div>
		<script>
		
		function isValidUsername(){
			var username = $("#username"); 
			if(username.val() === ''){
				username.addClass("is-invalid");
				username.removeClass("is-valid");
				$("#alert-username").show();
				return false;
		  	} 
	  		username.addClass("is-valid");
	  		username.removeClass("is-invalid");
	  		$("#alert-username").hide();
		  	return true;
		}
		function isValidPassword(){
			var password = $("#password");
			if(password.val() === ''){
				password.addClass("is-invalid");
				password.removeClass("is-valid");
				$("#alert-password").show();
				return false;
  			} 
			password.addClass("is-valid");
			password.removeClass("is-invalid");
			$("#alert-password").hide();
			return true;
		}
		
		
		
		$(document).ready(function () {
			$("#username").change(function(){
				isValidUsername();
			});
			$("#password").change(function(){
				isValidPassword();
		    });
			$("#submit-btn").click(function () {
				var a = isValidUsername();
				var b = isValidPassword();
				if(a && b)
					$("#login-form").submit();
			});
		});
		</script>
		
	</jsp:body>
</t:commonLayout>