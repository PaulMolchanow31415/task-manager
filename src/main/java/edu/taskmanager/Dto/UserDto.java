package edu.taskmanager.Dto;

import edu.taskmanager.Validator.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link edu.taskmanager.Model.UserEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
	@UniqueEmail
	@Email
	@NotBlank(message = "Email не должно быть пустым")
	private String email;
	
	@NotBlank(message = "Заполните пароль")
	private String password;
}