package com.example.demo.controller;

import com.example.demo.model.AdminData;
import com.example.demo.repository.AdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React
public class AdminController {
    
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final AdminRepository adminRepository;

    // Constructor for dependency injection
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // Create new admin data
    @PostMapping("/create")
    public ResponseEntity<AdminData> createAdmin(@RequestBody AdminData adminData) {
        logger.info("Creating new admin: {}", adminData.getName());
        AdminData savedAdmin = adminRepository.save(adminData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
    }

    // Update existing admin data
    @PutMapping("/update/{id}")
    public ResponseEntity<AdminData> updateAdmin(@PathVariable String id, @RequestBody AdminData adminData) {
        logger.info("Updating admin with ID: {}", id);
        Optional<AdminData> existingAdmin = adminRepository.findById(id);
        
        if (existingAdmin.isEmpty()) {
            logger.warn("Admin not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        adminData.setId(id);  // Ensure the admin's ID remains unchanged
        AdminData updatedAdmin = adminRepository.save(adminData);
        return ResponseEntity.ok(updatedAdmin);
    }

    // Delete an admin by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String id) {
        logger.info("Deleting admin with ID: {}", id);
        Optional<AdminData> existingAdmin = adminRepository.findById(id);

        if (existingAdmin.isEmpty()) {
            logger.warn("Admin not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        adminRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Check if the user is logging in for the first time
    @GetMapping("/first-time/{userid}")
    public ResponseEntity<String> checkFirstTimeLogin(@PathVariable String userid) {
        logger.info("Checking if user with ID: {} is logging in for the first time", userid);
        Optional<AdminData> existingAdmin = adminRepository.findByUserid(userid);

        if (existingAdmin.isEmpty()) {
            logger.info("User with ID: {} is logging in for the first time.", userid);
            return ResponseEntity.ok("First time login");
        }

        logger.info("User with ID: {} has logged in before.", userid);
        return ResponseEntity.ok("Welcome back");
    }

    // Get all admins
    @GetMapping
    public List<AdminData> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Get an admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<AdminData> getAdminById(@PathVariable String id) {
        Optional<AdminData> admin = adminRepository.findById(id);
        return admin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Get admin by email
@GetMapping("/email/{email}")
public ResponseEntity<AdminData> getAdminByEmail(@PathVariable String email) {
    Optional<AdminData> admin = adminRepository.findByEmail(email);
    return admin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
}

}
