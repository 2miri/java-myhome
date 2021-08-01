<%@page import="myhome.domain.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="myhome.domain.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<t:commonLayout title="Manage" tab="manage">
	<jsp:body>
	
	
	<table class="table table-striped table-dark table-bordered">
		<thead class="thead-dark">
		<tr>
			<th scope="col">회원번호</th>
			<th scope="col">아이디</th>
			<th scope="col">등록일자</th>
			<th scope="col">닉네임</th>
			<th scope="col">유형</th>
		</tr>
		</thead>
		<tbody class="tbody-white">
		<c:forEach var="dto" items="${memberList }">
			<tr>
				<td scope="row">${dto.no }</td>
				<td>${dto.username }</td>
				<td>${dto.regdate }</td>
				<td>${dto.nickname }</td>
				<td>${dto.type eq 0 ? '관리자' : '일반회원'}</td>
				
				<td>
				<c:if test="${ dto.type ne 0 }">
				
				<form>
				<input type = "hidden" name ="no" value = "${ dto.no }">
				<a id="target-${dto.no }" class="badge badge-danger delete-btn" type="button">강퇴</a>
				
				</form>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
<script>
$(document).ready(function(){
	$(".delete-btn").click(function(){
		console.log($(this) +"클릭됨!");
		$.get(
			"${pageContext.request.contextPath }/member/manageDelete", 
			$(this).parent().serialize(), // 서버에게 보낼 데이터(문자열, json)
			function (data){ // response 날아오면 할 일 (data 매개변수 : 서버로부터 받은 데이터)
				console.log(data);
				if(data['result']){
					targetId = "#target-" + data['no'];
					$(targetId).text("강퇴됨");
					$(targetId).addClass("badge-warning");
					$(targetId).removeClass("badge-danger");
					$(targetId).off("click");
					alert("강퇴 완료!");
				} else {
					alert("강퇴 실패..");
				}
			},
			"json" // 서버에게 받은 데이터의 content 타입(나는 json 받을거야!)
		);
	});
});



</script>
	
	
	</jsp:body>
</t:commonLayout>





