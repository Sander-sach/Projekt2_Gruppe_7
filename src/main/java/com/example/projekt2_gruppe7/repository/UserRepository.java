package com.example.projekt2_gruppe7.repository;

import com.example.projekt2_gruppe7.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> users = new ArrayList<>();

    public void save(User user){
        users.add(user);
    }

    public User findByEmail(String email){
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public List<User> findUser(){
        return users;
    }
}
