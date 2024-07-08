package com.atlacademy.customers.controllers;

import com.atlacademy.customers.entities.User;
import com.atlacademy.customers.services.UserService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService service = new UserService();

    @GetMapping("/user")
    public List<User> getAllUsers(String token) {
        return service.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Integer id) {
        return service.getUser(id);
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        String hashPassword = Hashing.sha256()
                        .hashString(user.getPassword(), StandardCharsets.UTF_8)
                        .toString();

        user.setPassword(hashPassword);

        service.addUser(user);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        service.updateUser(id, updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public void removeUser(@PathVariable Integer id) {
        service.removeUser(id);
    }

    @GetMapping("/user/find")
    public List<User> searchUsers(@RequestParam(name="email", required=false) String email,
                                          @RequestParam(name="address", required=false) String address) {
        return service.searchUsers(email, address);
    }
}
