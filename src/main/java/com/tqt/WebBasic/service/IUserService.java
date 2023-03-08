package com.tqt.WebBasic.service;

import com.tqt.WebBasic.model.LoginRequestDTO;
import com.tqt.WebBasic.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    //Add
    public User addUser(User user);
    //Update
    public User updateUser(int id, User user);
    //Delete
    public boolean deleteUser(int id);
    //GetOneId
    public Optional<User> getOneUser(int id);
    //GetAll
    public List<User> getAllUser();
    //Login
    public User login(LoginRequestDTO loginRequestDTO);
}
