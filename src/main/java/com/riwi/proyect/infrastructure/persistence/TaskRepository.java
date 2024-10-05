package com.riwi.proyect.infrastructure.persistence;

import com.riwi.proyect.domain.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
}
