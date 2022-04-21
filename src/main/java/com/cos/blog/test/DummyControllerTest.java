package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepositoory;


//html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI)
	private UserRepositoory userRepository;
	
	// http://localhost:8000/blog/dummy/user/1
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		//}catch (EmptyResultDataAccessException e) { // 정확하게 에러 잡을때의 
		}catch (Exception e) {
			// TODO: handle exception
			return "삭제에 실패하였습니다. 해당 아이디는 DB에 없습니다.";
		}
		return "삭제 되었습니다." + id;
				
	}
	
	
	
	
	
	
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에대한 데이터가 있으면 update를 해주고
	// 데이터가 없으면 insert를 해준다. 
	// http://localhost:8000/blog/dummy/user/1
	@Transactional  // 함수 종료시에 자동 commit됨.. 
	@PutMapping("/dummy/user/{id}") // update는 put을 사용해야 하고, detail()함수와 매핑된 주소가 같지만 put 과 get을 사용해 스프링이 알아서 구별한다. 
	public User updateUser(@ PathVariable int id , @RequestBody User requestUser) {// json데이터를 요청 => Java Object로 자동 변환
		// json으로 받아보기 MessaveConverter의 Jackson라이브러리가 자동으로 변환.. 
		System.out.println("id:" + id);
		System.out.println("password:" + requestUser.getPassword());
		System.out.println("email:" + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow( () ->{ 
			return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// 업데이트하기
	    //userRepository.save(user); // save는 insert문 아닌가? 근데 save에 id값을 넘겨주고 id값이 있으면 강제업데이트 해준다. 쓸수 있기는하다. 
		
		// 더티 체킹
		
		return user;
	}
	
	
	
	
	
	
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
		    
	}
	
	// 한 페이지당 2건에 데이터를 리턴받기.. 
	// size : 한페이지에서 보일 객체 갯수 , sort : 정렬 기준 , direction: 오름차순,내림차순 , Pageable : 이객체에 select결과가 저장
	@GetMapping("/dummy/user")
	public List<User> pageList( @PageableDefault(size=2,sort="id", direction = Sort.Direction.DESC) Pageable pageable ){
		
		// 스프링 부트에서 제공하는 페이징 기능.. 
		Page<User> pagingUser =   userRepository.findAll(pageable);
		
		
		if(pagingUser.isFirst()) { // 첫번째 페이지 인가요?
			
		}else if(pagingUser.isLast()) {// 마지막 페이지인가요?
			
		}
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	
	
	
	
	
	
	
	
	
	//{id} 주소로 파라메터를 전달 받을 수 있음
	// hppt://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return null 이 리턴 되자나.. 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!
		// null일 리는 절대 없어 : userRepository.findById(id).get()
		// id값이 없으면 null 반환하면 에러나니까 orElseThrow를 타라: -> 있으면 정상동작 . 없으면 에러가뜨는건 똑같지만 메세지 반환해준다. .. 
		
//		// 람다식으로 쉽게 사용하기? ()->{return new IllegalArgumentException("해당 유저는 없습니다. id :"+id)}
//		User user = userRepository.findById(id).orElseThrow( ()->{return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
//				});
		User user = userRepository.findById(id).get();
		
		
		// 요청: 웹브라우저
		// user 객체 = 자바 오브젝트 즉 브라우져가 이해못한다. 
		// 변환( 웹브라우저가 이해할 수 있는 데이터) -> json 
		// 스프링 부트 = MessageConverter라는 애가응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 전달한다. 
		// 그래서 브라우저에서는 json형태로 보여주는 것이다. 
		return user;
	}
		

	
	// http://localhost:8000/blog/dummy/join( 요청)
	// http의 body에 username, password, email데이터를 가지고 (요청)
	@PostMapping("/dummy/join") //post방식
	public String  join(@RequestBody User user) {// key=value(약속된 규칙) 이면 그냥해도 되고,, 키벨류가 아니면 @RequestBody 넣에줘야 파싱이된다. 
			
		System.out.println("id:" + user.getId());// 당연히 0.. 안보냈으니까
		System.out.println("username:" + user.getUsername());
		System.out.println("password:" + user.getPassword());
		System.out.println("email:" + user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user); 
		
		return "회원가입이 완료되었습니다.";      
	}
}
