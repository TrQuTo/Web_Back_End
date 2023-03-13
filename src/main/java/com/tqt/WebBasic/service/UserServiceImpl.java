package com.tqt.WebBasic.service;

import com.tqt.WebBasic.model.LoginRequestDTO;
import com.tqt.WebBasic.model.User;
import com.tqt.WebBasic.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public User addUser(User user) throws SQLIntegrityConstraintViolationException {
        User userName = userRepository.findByUsername(user.getUsername());
        User mail = userRepository.findByMail(user.getMail());
        if (userName != null) {
            throw new EntityExistsException("Username exist");
        } else if (mail != null) {
            throw new EntityExistsException("Mail exist");
        } else if (user.getRole_id() == null ) {
            throw new SQLIntegrityConstraintViolationException("Role is null");
        } else if (user.getRole_id() < 1 || user.getRole_id() > 2) {
            throw new SQLIntegrityConstraintViolationException("Role is not found");
        } else {
                Date date = new Date();
                System.out.println("Original Date: " + date);
                // Specify format as "yyyy-MM-dd hh:mm:ss"
                SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                //Date now when create user
                user.setRegistrationdate(dmyFormat.format(date));
                //BCrypt for Password
                user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
                return userRepository.save(user);
        }
    }
    @Override
    public User updateUser(int id, User user) {
        if(user != null){
            User userUpdate = userRepository.getReferenceById(id);
            userUpdate.setUsername(user.getUsername());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setPhone(user.getPhone());
            userUpdate.setMail(user.getMail());
            userUpdate.setBirthday(user.getBirthday());
            userUpdate.setRegistrationdate(user.getRegistrationdate());
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
        String username = loginRequestDTO.getUsername();
        String password = loginRequestDTO.getPassword();
        User user = userRepository.findByUsername(username);
        boolean passwordMatches = false;
        //User exist or not exist
        if (user != null){
            //Check password "BCrypt"
            passwordMatches = BCrypt.checkpw(password, user.getPassword());
        }
        if (passwordMatches){

            return user;
        }else {
            return null;
        }
    }


}
