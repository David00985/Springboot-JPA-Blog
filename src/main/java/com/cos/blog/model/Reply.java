package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 시퀀스 사용
	private int id;
	
	@Column(nullable = false , length = 200)
	private String content;// 섬머노트 라이브러리 <html>태그가 섞어서 디자인됨.
	
	@ManyToOne // 여러개의 답변은 하나의 게시글에 존재 가능 
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne // 하나의 유저는 여러개의 답변을 달수있다. 
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate; 
	
	
}










