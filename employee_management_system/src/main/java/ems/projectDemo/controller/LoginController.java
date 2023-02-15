package ems.projectDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/loginPage")
	public String showMyLoginPage() {

		return "login";

	}

	// add request mapping for /access-denied

	@GetMapping("/accessDenied")
	public String showAccessDenied() {

		return "access-denied";

	}
}
