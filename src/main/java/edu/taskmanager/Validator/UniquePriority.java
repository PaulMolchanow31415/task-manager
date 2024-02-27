package edu.taskmanager.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniquePriorityValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface UniquePriority {
	String message() default "Такой приоритет уже существует!~";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
