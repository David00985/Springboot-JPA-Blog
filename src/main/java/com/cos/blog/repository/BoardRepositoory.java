package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;

// User테이블을 관리하고 pk값의 타입은 숫자야.. 
// DAO 
// 자동으로 bean으로 등록이 된다. 
//@Repository 생략 가능.. 
public interface BoardRepositoory extends JpaRepository<Board, Integer>{


	

}



// JAP Naming 쿼리 전략 
// select * from user where usrname = ? and password = ? 
//User findByUsernameAndPassword(String username , String password);

//@Query(value="select * from user where usrname = ? and password = ? " , nativeQuery = true)
//User login(String username , String password);







