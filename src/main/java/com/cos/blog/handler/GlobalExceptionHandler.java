package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResposeDto;


@ControllerAdvice  // 모든페이지에서 접근 가능하게 하려면
@RestController
public class GlobalExceptionHandler {
	
	//Exception 에러시 이함수가 자동 호출.. 
	@ExceptionHandler(value=Exception.class)
	public ResposeDto<String> handleArgumentException(Exception e) {
	return 	new ResposeDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value() , e.getMessage()); 
	
	}
	
}
