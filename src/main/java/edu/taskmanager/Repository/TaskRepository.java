package edu.taskmanager.Repository;

import edu.taskmanager.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	boolean existsByPriorityNot(Integer priority);
	
	List<Task> findAllByCreatorId(Long creator_id);
}
