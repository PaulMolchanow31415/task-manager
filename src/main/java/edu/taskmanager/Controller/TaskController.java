package edu.taskmanager.Controller;

import edu.taskmanager.Dto.TaskDto;
import edu.taskmanager.Security.UserPrincipal;
import edu.taskmanager.Service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
	private final TaskService service;
	
	@PostMapping("/store")
	String createTask(
			@Valid @ModelAttribute("task")
			TaskDto dto,
			Errors errors,
			@AuthenticationPrincipal UserPrincipal principal
	) {
		if (errors.hasErrors()) {
			return "edit";
		}
		
		service.store(dto, principal.getId());
		return "redirect:/task/all";
	}
	
	@GetMapping("/all")
	String showTasks(Model model) {
		model.addAttribute("tasks", service.all());
		return "tasks";
	}
	
	@GetMapping("/create")
	String showCreateForm(Model model) {
		model.addAttribute("task", new TaskDto());
		return "edit";
	}
	
	@GetMapping("/edit/{id}")
	String showTask(@PathVariable Long id, Model model) {
		model.addAttribute("task", TaskDto.from(service.get(id)));
		return "edit";
	}
	
	@GetMapping("/complete/{id}")
	String complete(@PathVariable Long id) {
		service.complete(id);
		return "redirect:/task/all";
	}
	
	@GetMapping("/destroy/{id}")
	String deleteTask(@PathVariable Long id) {
		service.destroy(id);
		return "redirect:/task/all";
	}
}
