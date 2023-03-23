package com.tqt.WebBasic.service;

import com.tqt.WebBasic.model.LoginRequestDTO;
import com.tqt.WebBasic.model.User;
import com.tqt.WebBasic.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User updateUser(int id, User user) {
        if (user != null) {
            User userUpdate = userRepository.getReferenceById(id);
            userUpdate.setFirstname(user.getFirstname());
            userUpdate.setLastname(user.getLastname());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setPhone(user.getPhone());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setBirthday(user.getBirthday());
            userUpdate.setImageUrl(user.getImageUrl());
            return userRepository.save(userUpdate);
        }
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<User> getOneUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User login(LoginRequestDTO loginRequestDTO) {
        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();
        Optional<User> user = userRepository.findByEmail(email);
        boolean passwordMatches = false;
        //User exist or not exist
        if (user != null) {
            //Check password "BCrypt"
            passwordMatches = BCrypt.checkpw(password, user.get().getPassword());
        }
        if (passwordMatches) {

            return null;
        } else {
            return null;
        }
    }


}
