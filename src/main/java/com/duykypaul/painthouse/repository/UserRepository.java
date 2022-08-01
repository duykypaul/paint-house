package com.duykypaul.painthouse.repository;

import com.duykypaul.painthouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String userName);

    Boolean existsByEmail(String email);
}
