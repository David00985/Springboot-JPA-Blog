
let index = {
	
	// let _this = this
	init: function(){
		$("#btn-save").on("click", () => { // function(){} 말고 화살표 쓰는 이유는 this를 바인딩하기 위해서?
			this.save();
			// function(){} 을 사용했으면 _this.save()를 해야 save함수에 접근 가능해진다. 
		});
		
	},
	
	save: function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
	
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
		// ajax호출시 default가 비동기 호출.. 
		$.ajax({
			type : "POST",
			url : "/api/board",
			data:JSON.stringify(data),//http body 데이터 // json타입으로 변경해서 전송해야 서버단에서 읽을수있다.  
			contentType : "application/json;charset=utf-8", // header에 body데이터가 뭔지 알려줘야 한다. MINE 타입이라고도한다. 
			dataType: "json" // 요청후 응답이 왔을때 기본적으로는 모든것이 String 이때 ( 생긴게 json이라면) => javascript오브젝트로 변경해준다. 
			
		}).done(function( resp ){
			alert("글쓰기가 완료되었습니다.");
			//console.log(resp);
			location.href="/"; // 메인페이지로 이동
		}).fail(function(err){
			alert(JSON.stringify(err));
		});
		
	}
	
	
}

index.init();


