package com.tqt.WebBasic.controller;

import com.tqt.WebBasic.model.LoginRequestDTO;
import com.tqt.WebBasic.model.User;
import com.tqt.WebBasic.service.IUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;
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
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.isAuthenticated()) {
//            System.out.println("Đã");
//            // Người dùng đã đăng nhập
//            // Lấy thông tin về người dùng từ auth.getPrincipal()
//            return iUserService.getOneUser(id);
//        } else {
//            System.out.println("Chưa");
//            return iUserService.getOneUser(id);
//            // Người dùng chưa đăng nhập
//        }
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
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response){
        User user = iUserService.login(loginRequestDTO);
        if(user != null){
            long currentTimeMillis = System.currentTimeMillis();
            long expirationTimeMillis = currentTimeMillis + 3600000; // Expire after 1 hour
            SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            Claims claims = Jwts.claims()
                    .setSubject(user.getUsername())
                    .setExpiration(new Date(expirationTimeMillis));
            claims.put("roles", user.getRole_id()); // user.getRoles() trả về danh sách các vai trò của người dùng
            String token = Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
            // Đặt JWT vào tiêu đề HTTP
            response.addHeader("Authorization", "Bearer " + token);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password does not exist");
        }
    }
}
