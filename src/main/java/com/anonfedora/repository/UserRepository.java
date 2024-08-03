package com.anonfedora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anonfedora.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
