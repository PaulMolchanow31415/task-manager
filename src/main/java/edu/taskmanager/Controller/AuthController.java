package edu.taskmanager.Controller;

import edu.taskmanager.Dto.UserDto;
import edu.taskmanager.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
	private final UserService service;
	
	@GetMapping("/login")
	String showLogin() {
		return "login";
	}
	
	@GetMapping("/registration")
	String showRegistration(Model model) {
		model.addAttribute("user", new UserDto());
		return "registration";
	}
	
	@PostMapping("/register")
	String register(@Valid @ModelAttribute("user") UserDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return "registration";
		}
		
		service.store(dto);
		return "redirect:/login";
	}
}

