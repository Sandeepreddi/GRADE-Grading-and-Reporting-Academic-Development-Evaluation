package com.example.demo.controller;

import com.example.demo.model.Admindata;
import com.example.demo.model.Residentdata;
import com.example.demo.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/residents")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from React
public class ResidentController {

    @Autowired
    private ResidentRepository residentRepository;

    // Endpoint to create a new resident
    @PostMapping
    public ResponseEntity<?> createResident(@RequestBody Residentdata resident) {
        try {
            Residentdata savedResident = residentRepository.save(resident);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedResident);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register resident");
        }
    }

    // Endpoint to fetch all residents
    @GetMapping
    public ResponseEntity<?> getAllResidents() {
        try {
            List<Residentdata> residents = residentRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(residents);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch residents");
        }
    }
    
    // Endpoint to fetch resident by email
@GetMapping("/{email}")
public ResponseEntity<?> getResidentByEmail(@PathVariable String email) {
    try {
        Optional<Residentdata> residentOptional = residentRepository.findByEmail(email);

        if (residentOptional.isPresent()) {
            return ResponseEntity.ok(residentOptional.get()); // Return found resident
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resident not found");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching resident data");
    }
}

@PutMapping("update/{email}")
public ResponseEntity<?> updateAdmin(@PathVariable String email, @RequestBody Residentdata updatedResident) {
    try {
        Optional<Residentdata> existingResidentOptional = residentRepository.findByEmail(email);

        if (existingResidentOptional.isPresent()) {
            Residentdata existingResident = existingResidentOptional.get();

            // Update fields
            existingResident.setName(updatedResident.getName());
            existingResident.setPhone_number(updatedResident.getPhone_number());
            existingResident.setSocietyName(updatedResident.getSocietyName());
            existingResident.setFlatNo(updatedResident.getFlatNo());
            existingResident.setPostal(updatedResident.getPostal());

            Residentdata savedAdmin = residentRepository.save(existingResident);
            return ResponseEntity.ok(savedAdmin);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resident not found");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Admin data");
    }
}




}