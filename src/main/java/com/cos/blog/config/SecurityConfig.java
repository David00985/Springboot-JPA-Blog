package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;


// 빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것


@Configuration   // 빈등록(IoC 관리) // 빈등록을 해야 스프링 컨테이너가 new 가능하다. 
@EnableWebSecurity // 시큐리티 필터 등록 //시큐리티 필터 추가 = 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다.  
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.  
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	@Bean // IoC 가 된다. 
	public BCryptPasswordEncoder encodePWD() {
		//String encPassword = new BCryptPasswordEncoder().encode("1234"); //1234를 해쉬암호화 한다. 
		return new BCryptPasswordEncoder();
	}
	
	
	// 시큐리티가 대신 로그인 해주는데 password를 가로채기를 하는데 
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
		// 여기서 1. username을 db에서 확인하고, 2. 비번 비교를 한다. 만든 2개의 함수를 이용해서( principalDetailService, encodePWD)
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // scrf 토큰 비활성화 ( 테스트시 걸어두는 게 좋음) 
			.authorizeRequests()
				.antMatchers( "/",  "/auth/**" , "/js/**" ,  "/css/**" ,  "/image/**"  )   // auth로시작하는건 다 ok , js파일 , css , image도 다 접근하기 
				.permitAll()  // /auth 는 누구나 입장 허가
				.anyRequest() // 그외에 나머지는
				.authenticated()  // 입장 불가능.. 에러페이지 나온다. 
			.and()  // 그리고
				.formLogin()// 권한없이 접근시에 에러페이지 말고 로그인 페이지 나오게 하게 하고 
				.loginPage("/auth/loginForm") // 그 주소는 다음과 같다. 그리고 로그인 아이디와 비번을 form으로 전송하는데 이때 
				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인을 해준다. 
				.defaultSuccessUrl("/"); //로그인이 성공하면 "/" 페이지로 이동한다.  실패시 이동하는 failureUrl() 도 있다. 
				
	}
}
