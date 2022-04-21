package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 시퀀스 사용
	private int id;
	
	@Column(nullable = false , length = 100)
	private String title;
	
	@Lob // 대용량 데이터 
	private String content;// 섬머노트 라이브러리 <html>태그가 섞어서 디자인됨.
	

	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER) // board= many , user=one    / 한명의 유저는 여러 개시글가능 
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할수 없다 . FK, 자바는 오브젝트를 저장할수있다. 
	// 이게 포링키.. 필드값: userId 
	
	// 게시글    유저  -> ManyToOne
	//  여러개   1명
	// 
	  
	@CreationTimestamp
	private Timestamp createDate;
	     
	//mappedBy 가있으면 난 포링키가 아니에요 즉 db에 컬럼을 만들지 마세요.. 
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // reply클래스의 private Board board; 변수명 쓰기
	private List<Reply> reply; // 연관관계 주인x , 조인문때 사용하려고 등록만한겁니다. 
	
	   
	// 게시글    댓글  -> OneToMany 
	//  1개      여러개
	// 
	
}
