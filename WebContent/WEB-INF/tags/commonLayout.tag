<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="title" required="false"%>
<%@attribute name="tab" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>MyHome ${ not empty title ? ':: '.concat(title) : '' }</title>
	<meta charset="UTF-8"/>
	<meta name = "viewport" 
		  content = "width=device-width, initial-scale=1.0"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<style>
	.fakeimg {
		height: 200px;
		background: #aaa;
	}
	</style>
</head>

<body class="container-fluid bg-dark">

<!-- header : nav -->
<div id="header-container" 
	 class="jumbotron text-center bg-dark text-white"
	 style="margin-bottom:0">
	 
	 <h1 class="display-2">My Home!</h1>
	 <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	 	<a id="current-user" class="navbar-brand">${sessionScope.currentDto != null ? currentDto.nickname : '로그인하세요.' }</a>
	 	<button class="navbar-toggler" type="button"
	 			data-toggle="collapse" data-target="#nav-items">
	 		<span class="navbar-toggler-icon"></span>		
	 	</button>
	 	
	 	<div class="collapse navbar-collapse" id="nav-items">
	 		<ul class="navbar-nav">
	 		
	 		
	 			<c:if test="${not empty currentDto }">
	 				<li class = "nav-item">
	 				<a class="nav-link ${tab eq 'logout' ? 'active' : '' }" href="${pageContext.request.contextPath }/logout">Logout</a>
	 				</li>
	 				<li class = "nav-item">
		 				<a class="nav-link ${tab eq 'mypage' ? 'active' : '' }" href="${pageContext.request.contextPath }/view/member/mypage">MyPage</a>
	 				</li>
	 			</c:if>
	 			
	 			<c:if test="${empty currentDto }">
		 			<li class = "nav-item">
		 				<a class="nav-link ${tab eq 'login' ? 'active' : '' }" href="${pageContext.request.contextPath }/view/member/login.jsp">Login</a>
	 				</li>
	 				<li class = "nav-item">
		 				<a class="nav-link ${tab eq 'join' ? 'active' : '' }" href="${pageContext.request.contextPath }/view/member/join.jsp">Join</a>
	 				</li>
 				</c:if>
 				
 				<li class = "nav-item">
	 				<a class="nav-link ${tab eq 'board' ? 'active' : '' }" href="${pageContext.request.contextPath }/board/list?page=1">Board</a>
 				</li>
 				<li class = "nav-item">
	 				<a class="nav-link ${tab eq 'file' ? 'active' : '' }" href="${pageContext.request.contextPath }/file/list">Files</a>
 				</li>
 				
 				<c:if test="${currentDto.type eq 0 }">
	 				<li class = "nav-item">
		 				<a class="nav-link ${tab eq 'manage' ? 'active' : '' }" href="${pageContext.request.contextPath }/view/member/manage">Manage</a>
	 				</li>
	 			</c:if>
	 		</ul>
	 	</div>
	 </nav>
</div>


<!-- 본문 -->
<div id="main-container" 
	style="max-width:72em; min-height:55%"
	class="bg-dark text-white container-fluid p-5 justify-content-center" 
	style="">
		<jsp:doBody/>
</div>

<!-- footer -->
<div id="footer-container" 
	class="footer jumbotron-fluid text-center p-3 text-white">
	<div class = "container">
		<p>&copy;2021. Miri Lee. All Rights Reserved.</p>
	</div>

</div>

</body>
</html>








