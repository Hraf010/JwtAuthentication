package com.hraf.dao;

import com.hraf.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
