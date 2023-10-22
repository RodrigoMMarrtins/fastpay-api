package com.example.fastpayapi.fastpayapi.controller;

import com.example.fastpayapi.fastpayapi.domain.User.User;
import com.example.fastpayapi.fastpayapi.dto.UserDTO;
import com.example.fastpayapi.fastpayapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    private ResponseEntity<User> create(
            @RequestParam UserDTO user
    ) {
        User newUser = this.service.createUser(user);
        return new ResponseEntity(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<User>> findUsers() {
        List<User> users = service.findUsers();
        return ResponseEntity.ok(users);
    }
}
