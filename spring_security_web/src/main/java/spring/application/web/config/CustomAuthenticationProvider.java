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

public class CustomAuthenticationProvider implements AuthenticationProvider {

	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String id = (String)authentication.getPrincipal();
		String password = (String)authentication.getCredentials();
		//form에서 전달 된, name태그 설정이 username-parameter, password-parameter로 되있는 값을 읽어온다.
		
		if(id.equals("fail")) {	
			return null;
		}
		
		User user = new User();
		user.setId(id);
		user.setPw(password);
		
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
