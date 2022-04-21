package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResposeDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;


	@PostMapping("/api/board") // 회원가입시에 필요한 페이지 허용.. 
	public ResposeDto<Integer> save(@RequestBody Board board , @AuthenticationPrincipal PrincipalDetail principal) {
	
		// 유저 정보 넣을려면 시큐리티 세션에서 가져와야 한다. 
		boardService.글쓰기(board , principal.getUser());
		return new ResposeDto<Integer>(HttpStatus.OK.value() , 1); // 자바 오브젝으를 JSON으로 변환해서 리턴(Jackson)
	}
	
	
	
	
}
