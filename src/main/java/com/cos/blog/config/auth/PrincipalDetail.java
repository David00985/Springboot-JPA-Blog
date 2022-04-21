package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Data;
import lombok.Getter;


// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다. 
@Getter
public class PrincipalDetail implements UserDetails{
	
	private User user; // 콤포지션 
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	// 비밀번호 get함수
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	// 유저 아이디 get 함수
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴한다. (True: 만료 안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	// 계정이 잠겨있지 않았는지 리턴한다( True:잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않았는지 리턴한다.( true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	// 계정이 활성화(사용가능)인지 리턴한다. (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정이 갖고 있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 한개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		
		/*
		 * collectors.add(new GrantedAuthority() {
		 * 
		 * @Override public String getAuthority() { // TODO Auto-generated method stub
		 * return "ROLE_"+user.getRole(); // ROLE_USER <- 앞에 ROLE은 규칙 } });
		 */
		
		collectors.add( ()->{return "ROLE_"+user.getRole(); } ); // 위에 주석 내용을 람다식으로 변경 
		// GrantedAuthority()을 들어가보면 함수가 getAuthority()하나밖에 없다. 그래서 많이 간추려 진다. 
		
		return collectors;
	}
	
}
