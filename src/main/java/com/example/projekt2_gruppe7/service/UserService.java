package com.example.projekt2_gruppe7.service;

import com.example.projekt2_gruppe7.model.User;
import com.example.projekt2_gruppe7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Boolean validateEmail(String email){
        User user = userRepository.findUserByEmail(email);

        if(user == null){
            return true;
        }
        return false;
    }
    private Boolean validatePassword(String password){

        if(password.matches(".*[0-9].*") || password.length()>=8){
            return true;
        }
        return false;
    }

    public void registerUser(String name, String email, String password) {
        Boolean passwordValidated = validatePassword(password);
        Boolean emailValidated = validateEmail(email);
        User user = new User(name, email, password);

        if(passwordValidated && emailValidated){
        userRepository.createUser(user);
        }
    }

    public User getUser(String email, String password){
        User user = null;
        Boolean passwordvalidated = validatePassword(password);

        if(passwordvalidated){ user = userRepository.findUserByEmailAndPassword(email, password);}

        return user;
    }
}
