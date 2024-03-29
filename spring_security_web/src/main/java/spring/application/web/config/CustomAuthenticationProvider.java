package spring.application.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import spring.application.web.dto.User;

/*
 * 실질적으로 인증 절차가 이뤄지는 곳이다.
 * 서비스단으로부터 db에 저장된 사용자 정보와 비교 후 넘어온 리턴 객체를 가지고 
 * Authentication 객체를 생성하거나 인증되지 못하는 경우 예외를 넘겨주는 역할을 한다.
 * (해당 코딩에서는 DB를 따로 구현을 안했다.)
 * 
 * */

public class CustomAuthenticationProvider implements AuthenticationProvider {

	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String id = (String)authentication.getPrincipal(); //보호받는 Resource에 접근하는 대상 쉽게 말해, id
		String password = (String)authentication.getCredentials(); //Resource에 접근하는 대상의 비밀번호
		//form에서 전달 된, name태그 설정이 username-parameter, password-parameter로 되있는 값을 읽어온다.
		
		if(id.equals("fail")) {	
			return null;
		}
		
		User user = new User();
		user.setId(id);
		user.setPw(password);
		
		/*
		 * GrantedAuthority는 현재 사용자(Principal)가 가지고 있는 권한을 의미하며, ROLE_ADMIN이나, ROLE_USER같이
		 * ROLE_*의 형태로 사용한다. GrantedAutority 객체는 UserDetailsService에 의해 불러올 수 있고,특정 자원에 대한 
		 * 권한이 있는지를 검사하여 접근 허용 여부를 결정한다.
		 * 
		 * 
		 * */
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(id, password, roles);
		result.setDetails(user);
		//user session 생성 후 변환
		
		
		return result;
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	//Spring Security의 AuthenticationProvider을 구현한 클래스로  security-context에 provider로 등록 후 인증 절차를 구현 
	//login view에서 login-processing-url로의 form action 진행 시 해당 클래스의 supports() > authenticate()순으로 인증 절차 진행
	
	
	
	
}
