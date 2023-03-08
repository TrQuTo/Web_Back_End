package com.tqt.WebBasic.repository;

import com.tqt.WebBasic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    User findByMail(String mail);

}
