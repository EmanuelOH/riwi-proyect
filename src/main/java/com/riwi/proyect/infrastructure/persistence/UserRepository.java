package com.riwi.proyect.infrastructure.persistence;

import com.riwi.proyect.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsernameOrEmail (String username, String email);
}
