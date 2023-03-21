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

    @Autowired
    UserRepository userRepository;
    @Override
    public User addUser(User user) throws SQLIntegrityConstraintViolationException {
        User userName = userRepository.findByEmail(user.getEmail());
        if (userName != null) {
            throw new EntityExistsException("Mail exist");
        } else {
            //BCrypt for Password
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
            return userRepository.save(user);
        }
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
            userUpdate.setRegistrationDate(user.getRegistrationDate());
            userUpdate.setImage_url(user.getImage_url());
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
        User user = userRepository.findByEmail(email);
        boolean passwordMatches = false;
        //User exist or not exist
        if (user != null) {
            //Check password "BCrypt"
            passwordMatches = BCrypt.checkpw(password, user.getPassword());
        }
        if (passwordMatches) {

            return user;
        } else {
            return null;
        }
    }


}
