package com.diploma.todolist.adaptor.persistence;

import com.diploma.todolist.adaptor.persistence.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
