package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")  // MongoDB Collection name
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private String role;
    private boolean isLoggedIn; // New field to track login status

    // Constructors
    public User() {}

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.isLoggedIn = false; // Default to false (first-time login)
    }

    public User(String email, String password, String role, boolean isLoggedIn) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.isLoggedIn = isLoggedIn;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isLoggedIn() { return isLoggedIn; } // Getter for isLoggedIn
    public void setLoggedIn(boolean isLoggedIn) { this.isLoggedIn = isLoggedIn; } // Setter for isLoggedIn
}