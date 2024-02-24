package edu.taskmanager.Validator;

import edu.taskmanager.Repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
	
	private final UserRepository repository;
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return repository.findByEmail(email).isEmpty();
	}
}
