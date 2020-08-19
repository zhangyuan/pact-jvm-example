package com.example.services;

import com.example.controllers.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${user-service.endpoint}")
    String endpoint;

    public UserEntity[] getUsers() {
        String url = endpoint + "/v1/users";
        return restTemplate.getForEntity(url, UserEntity[].class).getBody();
    }
}
