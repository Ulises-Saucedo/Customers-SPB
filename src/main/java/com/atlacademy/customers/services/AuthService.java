package com.atlacademy.customers.services;

import com.atlacademy.customers.entities.User;
import com.atlacademy.customers.repositories.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) {
        String hashPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        User user = userRepository.findByEmailAndPassword(email, hashPassword);

        return user;
    }
}
