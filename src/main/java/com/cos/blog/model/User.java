package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 빌더 패턴~~
@Entity  // User 클래스가 MySQL에 테이블이 생성이 된다. 
//@DynamicInsert // insert시에 null을 빼주는 것... 
public class User {
	   
	@Id // Primary Key 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto..increment
	
	@Column(nullable=false, length=30 , unique = true)// null 불가능, 글자제한 30
	private String username; //유저 아이디
	
	@Column(nullable=false, length=100)// null 불가능, 글자제한 100 => 왜길게? 해쉬로 변경(암호화)
	private String password; // 비밀번호
	
	@Column(nullable=false, length=50)
	private String email;
	     
	//@ColumnDefault("'user")  // 이방식을 안쓴다.. 자바코딩에서 직접 user를 넣어줄 것이다. 
	// DB는 RoleType이라는게 없기때문에 String 이라는걸 알려줘야 한다. 
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. // ADMIN , USER // 타입이 강제된다. 
	// enum 을쓰면 오타 Admnnnnn 이런거 방지할수 있다.. 
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate; // 가입날짜
	
	
	
	
}
