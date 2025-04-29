package com.example.demo.controller;

import com.example.demo.model.UserData;
import com.example.demo.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<UserData> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserData> getUser(@PathVariable String id) {
        return userRepository.findById(id);
    }

    // Signup
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserData user) {
        logger.info("Signup request received for email: {}", user.getEmail());

        Optional<UserData> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            logger.warn("Email already exists: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        UserData savedUser = userRepository.save(user);
        logger.info("User created successfully: {}", savedUser.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserData user) {
        logger.info("Login request received for email: {}", user.getEmail());

        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and password are required");
        }

        Optional<UserData> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        if (!existingUser.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        UserData loggedInUser = existingUser.get();
        boolean isFirstLogin = !loggedInUser.isLoggedIn();

        if (isFirstLogin) {
            loggedInUser.setLoggedIn(true);
            userRepository.save(loggedInUser);
        }

        return ResponseEntity.ok(new LoginResponse(
            loggedInUser.getRole(),
            loggedInUser.getEmail(),
            !isFirstLogin // if it's not first login, then form is filled
        ));
    }

    static class LoginResponse {
        private String role;
        private String email;
        private boolean isLoggedIn;

        public LoginResponse(String role, String email, boolean isLoggedIn) {
            this.role = role;
            this.email = email;
            this.isLoggedIn = isLoggedIn;
        }

        public String getRole() { return role; }
        public String getEmail() { return email; }
        public boolean getIsLoggedIn() { return isLoggedIn; }
    }
}
