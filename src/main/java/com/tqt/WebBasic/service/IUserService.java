package com.tqt.WebBasic.service;

import com.tqt.WebBasic.model.LoginRequestDTO;
import com.tqt.WebBasic.model.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface IUserService {
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
