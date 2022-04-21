package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResposeDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;


	@PostMapping("/auth/joinProc") // 회원가입시에 필요한 페이지 허용.. 
	public ResposeDto<Integer> save(@RequestBody User user) {
	
		System.out.println("UserApiController : save호출됨" + user);
		// 실제로 DB에 insert를 하고 아래에서 return 하기.. 	

		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResposeDto<Integer>(HttpStatus.OK.value() , 1); // 자바 오브젝으를 JSON으로 변환해서 리턴(Jackson)
	}
	
	
	
	
}
