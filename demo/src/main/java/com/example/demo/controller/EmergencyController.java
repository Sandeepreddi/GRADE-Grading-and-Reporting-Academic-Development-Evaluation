package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.EmergencyContacts;
import com.example.demo.repository.EmergencyRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emergency")
@CrossOrigin(origins = "http://localhost:5173")
public class EmergencyController {

    @Autowired
    private EmergencyRepository emergencyRepository;

    // Fetch all emergency contacts
    @GetMapping
    public ResponseEntity<List<EmergencyContacts>> getAllContacts() {
        List<EmergencyContacts> contacts = emergencyRepository.findAll();
        return ResponseEntity.ok(contacts);
    }

    // Add a new contact
    @PostMapping
    public ResponseEntity<EmergencyContacts> addContact(@RequestBody EmergencyContacts contact) {
        EmergencyContacts savedContact = emergencyRepository.save(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    // DELETE contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable String id) {
        Optional<EmergencyContacts> contact = emergencyRepository.findById(id);

        if (contact.isPresent()) {
            emergencyRepository.deleteById(id);
            return ResponseEntity.ok("Contact deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
        }
    }
}
