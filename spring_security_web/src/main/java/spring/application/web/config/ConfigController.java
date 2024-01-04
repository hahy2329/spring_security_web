package spring.application.web.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConfigController {
	
	@RequestMapping("/loginPage")
	public String login() {
		return "loginPage";
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@RequestMapping("/user")
	public String member() {
		return "user";
	}
	
	@RequestMapping("/all")
	public String all() {
		return "all";
				
	}
	
	@RequestMapping("/notBad")
	public String notBad() {
		return "notBad";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		
		return "logout";
	}
	
	
	
	
	
	
	
	
}
