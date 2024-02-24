package edu.taskmanager.Service;

import edu.taskmanager.Dto.UserDto;
import edu.taskmanager.Model.Role;
import edu.taskmanager.Model.UserEntity;
import edu.taskmanager.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	
	public void store(UserDto dto) {
		var user = new UserEntity();
		user.setEmail(dto.getEmail());
		user.setRoles(Collections.singleton(Role.USER));
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		repository.save(user);
	}
	
	public void destroy(Long id) {
		repository.deleteById(id);
	}
	
	public UserEntity get(Long id) {
		return repository.findById(id)
				.orElseThrow(() ->new RuntimeException("Ошибка, User не найден"));
	}
	
	public List<UserEntity> all() {
		return repository.findAll();
	}
}
