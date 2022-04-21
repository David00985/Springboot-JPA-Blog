package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Getter
//@Setter
//@Data  // getter setter 만들어준다. 
//@AllArgsConstructor // 생성자 만들어줌
//@RequiredArgsConstructor // final 붙은 애들에 대한 생성자 만들어준다. 

@Data 
@NoArgsConstructor 
public class Member {
	private  int id;
	private  String username;
	private  String password;
	private  String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
}
