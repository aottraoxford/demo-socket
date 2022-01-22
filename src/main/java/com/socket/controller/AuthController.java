package com.socket.controller;

import com.socket.payload.Response;
import com.socket.payload.request.SignIn;
import com.socket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private Response response;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> signIn(@RequestBody SignIn signIn) {
        return response.data(userService.signIn(signIn)).end();
    }
}
