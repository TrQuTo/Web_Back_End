package com.tqt.WebBasic.controller;

import com.tqt.WebBasic.model.LoginRequestDTO;
import com.tqt.WebBasic.model.User;
import com.tqt.WebBasic.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(path = "users")
public class UserController {
    @Autowired
    IUserService iUserService;
    @GetMapping("")
    public List<User> showAllUser(){
        return iUserService.getAllUser();
    }
    @GetMapping("/{id}")
    public Optional<User> showOneUser(@PathVariable("id") int id){
        return iUserService.getOneUser(id);
    }
    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return iUserService.addUser(user);
    }
    @PutMapping("/update")
    public User editUser(@RequestParam("id") int id, @RequestBody User user){
        return iUserService.updateUser(id, user);
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable("id") int id){
        return iUserService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO){
        User user = iUserService.login(loginRequestDTO);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password does not exist");
        }
    }
}
