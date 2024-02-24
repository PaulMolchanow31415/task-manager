package edu.taskmanager.Controller;

import edu.taskmanager.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final UserService service;
	
	@GetMapping("/all")
	String showUsers(Model model) {
		model.addAttribute("users", service.all());
		return "users";
	}
	
	@GetMapping("/destroy/{id}")
	String deleteUser(@PathVariable Long id) {
	  service.destroy(id);
	  return "redirect:/user/all";
	}
	
}
