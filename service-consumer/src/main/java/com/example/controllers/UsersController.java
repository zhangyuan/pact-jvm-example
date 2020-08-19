package com.example.controllers;

import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public UserEntity[] index() {
        return userService.getUsers();
    }
}
