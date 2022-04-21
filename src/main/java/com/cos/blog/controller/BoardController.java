package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	//@AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"" , "/"})
	public String index( Model model  , @PageableDefault(size=3,sort="id", direction = Sort.Direction.DESC) Pageable pageable ) {
	//	System.out.println("로그인 사용자 아이디:" + principal.getUsername());
		
		// 인덱스 페이지로 가기전에 게시글 정보를 가져가야한다. 
		model.addAttribute("boards" , boardService.글목록(pageable));
		return "index";
	}
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id , Model model) {
	
		model.addAttribute("board" , boardService.글상세보기(id));
		return "board/detail";
	}
	
	
	// user 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	
	
	
	
}
