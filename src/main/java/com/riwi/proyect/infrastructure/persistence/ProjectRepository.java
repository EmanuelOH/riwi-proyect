package com.riwi.proyect.infrastructure.persistence;

import com.riwi.proyect.domain.entities.Project;
import com.riwi.proyect.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUsersContains(Users user);
}
