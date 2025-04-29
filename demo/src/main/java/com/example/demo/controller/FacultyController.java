package com.example.demo.controller;

import com.example.demo.model.FacultyData;
import com.example.demo.repository.FacultyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
@CrossOrigin(origins = "http://localhost:3000")
public class FacultyController {

    private static final Logger logger = LoggerFactory.getLogger(FacultyController.class);

    private final FacultyRepository facultyRepository;

    // Constructor to inject FacultyRepository
    public FacultyController(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    // Create a new faculty record
    @PostMapping("/create")
    public ResponseEntity<FacultyData> createFaculty(@RequestBody FacultyData facultyData) {
        try {
            FacultyData savedFaculty = facultyRepository.save(facultyData);
            return new ResponseEntity<>(savedFaculty, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating faculty", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing faculty record
    @PutMapping("/{id}")
    public ResponseEntity<FacultyData> updateFaculty(@PathVariable String id, @RequestBody FacultyData facultyData) {
        Optional<FacultyData> existingFaculty = facultyRepository.findById(id);

        if (existingFaculty.isPresent()) {
            FacultyData updatedFaculty = existingFaculty.get();
            updatedFaculty.setName(facultyData.getName());
            updatedFaculty.setDepartment(facultyData.getDepartment());
            // Add more fields to update if necessary

            FacultyData savedFaculty = facultyRepository.save(updatedFaculty);
            return new ResponseEntity<>(savedFaculty, HttpStatus.OK);
        } else {
            logger.warn("Faculty with id {} not found", id);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a faculty record
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFaculty(@PathVariable String id) {
        try {
            Optional<FacultyData> faculty = facultyRepository.findById(id);

            if (faculty.isPresent()) {
                facultyRepository.delete(faculty.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.warn("Faculty with id {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error deleting faculty with id {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all faculty records
    @GetMapping
    public ResponseEntity<List<FacultyData>> getAllFaculty() {
        try {
            List<FacultyData> facultyList = facultyRepository.findAll();
            return new ResponseEntity<>(facultyList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching faculty records", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a specific faculty by ID
    @GetMapping("/{id}")
    public ResponseEntity<FacultyData> getFacultyById(@PathVariable String id) {
        Optional<FacultyData> faculty = facultyRepository.findById(id);

        if (faculty.isPresent()) {
            return new ResponseEntity<>(faculty.get(), HttpStatus.OK);
        } else {
            logger.warn("Faculty with id {} not found", id);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Fetch faculty by userid
@GetMapping("/userid/{userid}")
public ResponseEntity<FacultyData> getFacultyByUserid(@PathVariable String userid) {
    Optional<FacultyData> faculty = facultyRepository.findByUserid(userid);

    if (faculty.isPresent()) {
        return new ResponseEntity<>(faculty.get(), HttpStatus.OK);
    } else {
        logger.warn("Faculty with userid {} not found", userid);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}

@PutMapping("/userid/{userid}")
public ResponseEntity<FacultyData> updateFacultyByUserid(@PathVariable String userid, @RequestBody FacultyData facultyData) {
    Optional<FacultyData> existingFaculty = facultyRepository.findByUserid(userid);

    if (existingFaculty.isPresent()) {
        FacultyData updatedFaculty = existingFaculty.get();
        updatedFaculty.setProfession(facultyData.getProfession());
        updatedFaculty.setAddress(facultyData.getAddress());
        updatedFaculty.setPhoneno(facultyData.getPhoneno());
        updatedFaculty.setEmail(facultyData.getEmail());
        // Add more fields to update if necessary

        FacultyData savedFaculty = facultyRepository.save(updatedFaculty);
        return new ResponseEntity<>(savedFaculty, HttpStatus.OK);
    } else {
        logger.warn("Faculty with userid {} not found", userid);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}


}
