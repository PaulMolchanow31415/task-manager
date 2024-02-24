package edu.taskmanager.Validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface UniqueEmail {
	String message() default "Пользователь с таким email уже занят";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
