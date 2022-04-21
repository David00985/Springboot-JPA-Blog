<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
<div class="container">
	<form action="/auth/loginProc" method="post"> <!-- 스프링 시큐리티가 form안에 데이터를 가로챈다.  -->
		<div class="form-group">
			<label for="username">username:</label> 
			<input type="text" class="form-control" placeholder="Enter username" id="username" name="username">
		</div>

		<div class="form-group">
			<label for="pwd">Password:</label> 
			<input type="password" class="form-control" placeholder="Enter password"  id="pwd" name="password">
		</div>


		<button id="btn-login" class="btn btn-primary">로그인</button>
	</form>

</div>

<%@include file="../layout/footer.jsp"%>