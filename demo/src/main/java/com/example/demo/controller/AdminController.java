package com.example.demo.controller;

import com.example.demo.model.Admindata;
import com.example.demo.model.Residentdata;
import com.example.demo.repository.AdminRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from React
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping
    public ResponseEntity<?> createResident(@RequestBody Admindata resident) {
        try {
            Admindata savedAdmin = adminRepository.save(resident);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register Admin");
        }
    }

     // Endpoint to fetch resident by email
@GetMapping("/{email}")
public ResponseEntity<?> getAdminByEmail(@PathVariable String email) {
    try {
        Optional<Admindata> adminOptional = adminRepository.findByEmail(email);

        if (adminOptional.isPresent()) {
            return ResponseEntity.ok(adminOptional.get()); // Return found resident
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching Admin data");
    }
}

@PutMapping("update/{email}")
public ResponseEntity<?> updateAdmin(@PathVariable String email, @RequestBody Admindata updatedAdmin) {
    try {
        Optional<Admindata> existingAdminOptional = adminRepository.findByEmail(email);

        if (existingAdminOptional.isPresent()) {
            Admindata existingAdmin = existingAdminOptional.get();

            // Update fields
            existingAdmin.setName(updatedAdmin.getName());
            existingAdmin.setPhone_number(updatedAdmin.getPhone_number());
            existingAdmin.setSocietyName(updatedAdmin.getSocietyName());
            existingAdmin.setSocietyAddress(updatedAdmin.getSocietyAddress());
            existingAdmin.setCity(updatedAdmin.getCity());
            existingAdmin.setDistrict(updatedAdmin.getDistrict());
            existingAdmin.setPostal(updatedAdmin.getPostal());

            Admindata savedAdmin = adminRepository.save(existingAdmin);
            return ResponseEntity.ok(savedAdmin);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Admin data");
    }
}
}