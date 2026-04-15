package com.example.projekt2_gruppe7.Service;

import com.example.projekt2_gruppe7.model.User;
import com.example.projekt2_gruppe7.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private com.example.projekt2_gruppe7.service.UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_validInput_shouldCallCreateUser() {
        String name = "Valdemar";
        String email = "valdebloch@gmail.com";  // Skriv en email som ikke kan findes i databasen
        String password = "password1";

        when(userRepository.findUserByEmail(email)).thenReturn(null);
        userService.registerUser(name, email, password);

        //tjekker om createUser() rent faktsik bliver kaldt
        verify(userRepository, times(1)).createUser(any(User.class));

    }
    // En dublikeret Email - createUser må ikke kaldes
    @Test
    void registerUser_duplicateEmail_shouldNotCreateUser() {
        String name = "Valdemar";
        String email = "valdebloch@gmail.com";
        String password = "password1";

        User existingUser = new User(name, email, password);

        when(userRepository.findUserByEmail(email)).thenReturn(existingUser);
        userService.registerUser(name, email, password);

        verify(userRepository, never()).createUser(any(User.class));
    }

    // Ugyldig password - createUser må ikke kaldes
    @Test
    void regiserUser_indvalidPassword_shouldNotCreateUser() {
        String name = "Sander";
        String email ="Sander@email.com";
        String password = "ABC"; // Her er mindre end 8 tegn og ingen tal

        when(userRepository.findUserByEmail(email)).thenReturn(null);
        userService.registerUser(name, email, password);
        verify(userRepository, never()).createUser(any(User.class));
    }
    @Test
    void getUser_validCredentials_shouldReturnUser(){
        String name = "Allan";
        String email = "Allan@mail.dk";
        String password = "password1";

        User expectedUser = new User("Allan", email, "password1");

        when(userRepository.findUserByEmailAndPassword(email, password)).thenReturn(expectedUser);

        User result = userService.getUser(email, password);

        assertEquals(expectedUser,result);
        assertNotNull(result);
    }

    }
