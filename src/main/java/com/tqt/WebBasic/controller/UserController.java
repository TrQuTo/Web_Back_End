package com.tqt.WebBasic.controller;

import com.tqt.WebBasic.model.LoginRequestDTO;
import com.tqt.WebBasic.model.User;
import com.tqt.WebBasic.service.IGeneralService;
import com.tqt.WebBasic.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "users")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IGeneralService iGeneralService;

    @GetMapping()
    public List<User> showAllUser() {
        return iUserService.getAllUser();
    }

    @GetMapping("/{id}")
    public Optional<User> showOneUser(@PathVariable("id") int id) {
        Optional<User> user = iUserService.getOneUser(id);
        if (user.isEmpty()) {
            throw new NullPointerException("User with " + id + " not found");
        }
        return user;
    }

    @PutMapping("/update")
    public User editUser(@RequestParam("id") int id, @RequestBody User user) {
        if (iUserService.getOneUser(id).isEmpty()) {
            throw new EntityNotFoundException("User entity with id " + id + " exists");
        }
        return iUserService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable("id") int id) {
        if (iUserService.getOneUser(id).isEmpty()) {
            throw new EntityNotFoundException("User entity with id " + id + " exists");
        }
        return iUserService.deleteUser(id);
    }
}
