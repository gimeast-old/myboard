package com.gimeast.myboard.repository;

import com.gimeast.myboard.model.Role;
import com.gimeast.myboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
