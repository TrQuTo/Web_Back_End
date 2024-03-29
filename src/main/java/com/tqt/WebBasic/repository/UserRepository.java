package com.tqt.WebBasic.repository;

import com.tqt.WebBasic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsUserByEmail(String email);
}
