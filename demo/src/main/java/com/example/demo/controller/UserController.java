package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.Admindata;
import com.example.demo.model.Residentdata;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.ResidentRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from React
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable String id) {
        return userRepository.findById(id);
    }

    ///////////// Signup ///////////////
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        logger.info("Signup request received for email: {}", user.getEmail());

        // Check if the email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            logger.warn("Email already exists: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        // If email doesn't exist, save the new user
        User savedUser = userRepository.save(user);
        logger.info("User created successfully: {}", savedUser.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    ///////////// Login ///////////////
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        logger.info("Login request received for email: {}", user.getEmail());

        // Validate input
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            logger.warn("Invalid login request: Email or password is missing");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and password are required");
        }

        // Find user by email
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isEmpty()) {
            logger.warn("User not found for email: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Validate password
        if (!existingUser.get().getPassword().equals(user.getPassword())) {
            logger.warn("Invalid password for email: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Check if the user is logging in for the first time
        User loggedInUser = existingUser.get();
        if (loggedInUser.isLoggedIn()==false) {
            logger.info("First-time login for email: {}", user.getEmail());
            loggedInUser.setLoggedIn(true);
            userRepository.save(loggedInUser);
            return ResponseEntity.ok(new LoginResponse(loggedInUser.getRole(), false));
        } else {
            logger.info("Subsequent login for email: {}", user.getEmail());
            return ResponseEntity.ok(new LoginResponse(loggedInUser.getRole(), true));
        }
    }

    

    static class LoginResponse {
        private String role;
        private boolean isFormFilled;
    
        public LoginResponse(String role, boolean isFormFilled) {
            this.role = role;
            this.isFormFilled = isFormFilled;
        }
    
        public String getRole() { return role; }
    
        public boolean getIsFormFilled() { return isFormFilled; } // Change `isFormFilled()` to `getIsFormFilled()`
    }
}