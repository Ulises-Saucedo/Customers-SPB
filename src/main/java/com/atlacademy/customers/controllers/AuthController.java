package com.atlacademy.customers.controllers;

import com.atlacademy.customers.dto.RequestLogin;
import com.atlacademy.customers.entities.User;
import com.atlacademy.customers.services.AuthService;
import com.atlacademy.customers.utils.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/auth/login")
    public String login(@RequestBody RequestLogin requestLogin) {
        String email = requestLogin.getEmail();
        String password = requestLogin.getPassword();

        User user = authService.login(email, password);

        String token = JWT.createToken(user);

        return token;
    }
}
