package edu.taskmanager.Repository;

import edu.taskmanager.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	boolean existsByPriorityNot(Integer priority);
}
