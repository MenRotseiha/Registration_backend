package com.Men_Rotseiha.Registration.controller;


import com.Men_Rotseiha.Registration.entity.User;
import com.Men_Rotseiha.Registration.repository.UserRepository;
import com.Men_Rotseiha.Registration.service.imp.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api")
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserRepository repo;
/*
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        Optional<User> foundUser= repo.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if(foundUser.isPresent()) {
            userService.SaveUser(user);
            return ResponseEntity.ok("User registered successfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        Optional<User> foundUser= repo.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(foundUser.isPresent()) {
            return ResponseEntity.ok("User logged in successfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email or Password is incorrect");
        }
    }

 */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        boolean isRegistered = userService.registerUser(user);
        if (isRegistered) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or email already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticateUser(user);
        if (isAuthenticated) {
            return ResponseEntity.ok("User logged in successfully");
        } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or Password is incorrect");
            }
        }



}
