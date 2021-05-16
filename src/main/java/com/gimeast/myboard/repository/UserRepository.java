package com.gimeast.myboard.repository;

import com.gimeast.myboard.model.Board;
import com.gimeast.myboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
