package edu.taskmanager.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	@GetMapping("/")
	String show() {
		return "index";
	}

}
