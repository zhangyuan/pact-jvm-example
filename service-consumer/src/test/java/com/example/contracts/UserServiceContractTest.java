package com.example.contracts;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.example.controllers.UserEntity;
import com.example.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({SpringExtension.class, PactConsumerTestExt.class})
@SpringBootTest
@PactTestFor(providerName = "UserService", port = "8089")
public class UserServiceContractTest {
    @Autowired
    UserService userService;

    @Pact(provider="UserService", consumer="ConsumerApp")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
                .given("Users exist in user service")
                .uponReceiving("Get users list")
                .path("/v1/users")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .body("[{\"id\":\"1234569\", \"username\": \"jack\"}]")
                .toPact();
    }

    @Test
    void should_get_users() {
        UserEntity[] users = userService.getUsers();
        assertEquals(1, users.length);
        assertEquals("1234569", users[0].getId());
        assertEquals("jack", users[0].getUsername());
    }
}
