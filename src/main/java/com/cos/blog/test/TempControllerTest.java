package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		//파일 리턴 기본경로: src/main/resources/static
		//리턴명: /home.html
		//풀 경로: src/main/resources/static/home.html
		
		// 스프링부트는 jsp지원안해서 1. 디펜던시 추가해야하고 2.static파일안에는 jsp를 못넣기때문에 
		return "home";
	}
	// http://localhost:8000/blog/temp/jsp
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		return "test";   
	}
	
}
