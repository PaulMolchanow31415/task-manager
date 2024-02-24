package edu.taskmanager.Validator;

import edu.taskmanager.Repository.TaskRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueNumberValidator implements ConstraintValidator<UniqueNumber, Integer> {
	private final TaskRepository repository;
	
	@Override
	public boolean isValid(Integer number, ConstraintValidatorContext context) {
		if (repository.count() > 0) {
			return repository.existsByPriorityNot(number);
		}
		return true;
	}
}
