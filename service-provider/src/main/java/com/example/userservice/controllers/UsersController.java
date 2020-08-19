package com.example.userservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UsersController {
    @GetMapping("/v1/users")
    public List<UserEntity> index() {
        return Arrays.asList(new UserEntity("123456", "jack"));
    }
}
