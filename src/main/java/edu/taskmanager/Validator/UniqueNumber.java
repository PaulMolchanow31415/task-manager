package edu.taskmanager.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueNumber {
	String message() default "Такой приоритет уже существует!~";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
