package edu.taskmanager.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static edu.taskmanager.Model.TaskStatus.UNCOMPLETED;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "text", nullable = false)
	private String content;
	
	private Integer priority = 0;
	
	@ManyToOne
	private UserEntity creator;
	
	@Enumerated(STRING)
	private TaskStatus status = UNCOMPLETED;
	
	@Column(name = "dead_date")
	private LocalDateTime deadDate;
	@Column(name = "completed_at")
	private LocalDateTime completedAt;
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	@PrePersist
	private void onCreate() {
		createdAt = now();
		onUpdate();
	}
	
	@PreUpdate
	private void onUpdate() {
		updatedAt = now();
	}
}

