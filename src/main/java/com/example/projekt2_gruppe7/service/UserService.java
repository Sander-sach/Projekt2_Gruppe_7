package com.example.projekt2_gruppe7.service;

import com.example.projekt2_gruppe7.model.User;
import com.example.projekt2_gruppe7.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public String registerUser(UserModel user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return "Email mangler";

        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return "Password mangler";

        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Bruger findes allerede";
        }
        userRepository.save(user);
        return "Bruger oprettet!";
    }
}
