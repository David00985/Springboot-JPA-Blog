<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@include file="../layout/header.jsp"%>
<div class="container">
	<form>
		<!-- 스프링 시큐리티가 form안에 데이터를 가로챈다.  -->
		<div class="form-group">
			<label for="title">Title</label> <input type="text" class="form-control" placeholder="Enter title" id="title">
		</div>

		<div class="form-group">
			<label for="content">content</label>
			<textarea class="form-control  summernote" rows="5" id="content"></textarea>
		</div>

	</form>
		<button id="btn-save" class="btn btn-primary">글쓰기 완료</button>

</div>
<br />

<script>
	$('.summernote').summernote({
		//placeholder : 'Hello Bootstrap 4', // input의 그 placeholder 와 동일한거 
		tabsize : 2,
		height : 300
	});
</script>
<script type="text/javascript" src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>