package com.atlacademy.customers.services;

import com.atlacademy.customers.entities.User;
import com.atlacademy.customers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User getUser(Integer id) {
        Optional<User> user = repository.findById(id);

        if(user.isPresent()) {
            return user.get();
        }

        return null;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        Iterable<User> users = repository.findAll();
        for(User user : users) {
            list.add(user);
        }

        return list;
    }

    public void removeUser(Integer id) {
        repository.deleteById(id);
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public void updateUser(Integer id, User updatedUser) {
        updatedUser.setId(id);
        repository.save(updatedUser);
    }

    public List<User> searchUsers(String email, String address) {
        return repository.findByEmailOrAddress(email, address);
    }
}
