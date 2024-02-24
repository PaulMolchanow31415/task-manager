package edu.taskmanager.Service;

import edu.taskmanager.Dto.TaskDto;
import edu.taskmanager.Model.Task;
import edu.taskmanager.Repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

import static edu.taskmanager.Model.TaskStatus.COMPLETED;

@Service
@RequiredArgsConstructor
public class TaskService {
	private final TaskRepository repository;
	private final UserService userService;
	
	public void store(TaskDto d, Long creatorId) {
		val user = userService.get(creatorId);
		Task task = d.getId() == null ? new Task() : get(d.getId());
		task.setCreator(user);
		task.setId(d.getId());
		task.setContent(d.getContent());
		task.setPriority(d.getPriority());
		task.setDeadDate(d.getDeadDate());
		repository.save(task);
	}
	
	public void destroy(Long id) {
		repository.deleteById(id);
	}
	
	public Task get(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ошибка, Task не найден"));
	}
	
	public List<Task> all(Long creatorId) {
		return repository.findAllByCreatorId(creatorId);
	}
	
	public void complete(Long id) {
		val task = get(id);
		task.setStatus(COMPLETED);
		repository.save(task);
	}
}
