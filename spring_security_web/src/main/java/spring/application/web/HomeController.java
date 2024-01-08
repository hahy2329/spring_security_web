package spring.application.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.application.web.dto.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/loginSuccess")
	public String loginSuccess(HttpSession session, HttpServletRequest request) {
		
		//CustomAuthenticationProvider에서 set한 값을 로드 
		//즉, 프로젝트 내 어디서든 인증이 완료된 사용자 정보를 호출할 수 있도록 해주는 클래스이다.
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		//세션 설정
		session.setAttribute("id", user.getId());
		session.setAttribute("pw", user.getPw());
		
		return "loginSuccess";
	}
	
	@RequestMapping("/loginFail")
	public String loginFali() {
		return "loginFail";
	}
	
	@GetMapping("/myPage")
	public String myPage(HttpSession session) {
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		if(user != null) {
			
			session.setAttribute("id", user.getId());
			session.setAttribute("pw", user.getPw());
			
			return "myPage";
		}else {
			
			return "loginFail";
			
		}
		
	}
	
}
