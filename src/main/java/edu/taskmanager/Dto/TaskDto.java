package edu.taskmanager.Dto;

import edu.taskmanager.Model.Task;
import edu.taskmanager.Validator.UniquePriority;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Task}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto implements Serializable {
	private Long id;

	@NotBlank(message = "Пожалуйста заполните это поле")
	private String content;
	
	@PositiveOrZero(message = "Значение должно быть больше 0")
	@UniquePriority
	private Integer priority = 0;
	
	@Future(message = "Укажите правильную дату окончания задачи")
	private LocalDateTime deadDate;
	
	public static TaskDto from(Task task) {
		return new TaskDto(task.getId(), task.getContent(), task.getPriority(), task.getDeadDate());
	}
}