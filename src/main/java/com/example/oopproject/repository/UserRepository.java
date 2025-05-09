package com.example.oopproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.oopproject.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
        Optional<User> findUserByEmail(String email);
}
