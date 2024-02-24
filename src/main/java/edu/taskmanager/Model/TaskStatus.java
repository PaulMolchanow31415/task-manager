package edu.taskmanager.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TaskStatus {
	COMPLETED("Завершенный"),
	UNCOMPLETED("Не завершенный");
	
	private final String name;
}
