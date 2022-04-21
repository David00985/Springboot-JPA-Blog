package com.cos.blog.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepositoory;
import com.cos.blog.repository.UserRepositoory;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다. 

@Service
public class BoardService {

	@Autowired
	private BoardRepositoory boardRepository;
	

	@Transactional
	public void 글쓰기(Board board , User user) { // title , content
		board.setCount(0); // 조회수 처음은 0
		// 유저 정보 넣을려면 시큐리티 세션에서 가져와야 한다. 
		board.setUser(user);
		boardRepository.save(board);		
	}
	
	public Page<Board> 글목록(Pageable pageable){
		
		return boardRepository.findAll(pageable);
		
	}

	public Board 글상세보기(int id) {
		
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을수 없습니다.");
				});
		
	}
	

	
	
}
