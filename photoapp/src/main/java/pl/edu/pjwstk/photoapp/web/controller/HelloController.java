package pl.edu.pjwstk.photoapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("/")
	public String showHomePage() {
		return "/index";
	}
	@GetMapping("/contact")
	public String showContactPage() {
		return "/contact";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "/login";
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}

}
