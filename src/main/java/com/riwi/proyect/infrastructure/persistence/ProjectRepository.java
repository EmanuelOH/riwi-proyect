package com.riwi.proyect.infrastructure.persistence;

import com.riwi.proyect.domain.entities.Project;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
