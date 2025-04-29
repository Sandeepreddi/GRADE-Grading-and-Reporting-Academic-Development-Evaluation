package com.example.demo.controller;

import com.example.demo.model.StudentData;
import com.example.demo.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Optional;
@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE a new student
    @PostMapping("/create")
    public ResponseEntity<StudentData> createStudent(@RequestBody StudentData student) {
        try {
            StudentData savedStudent = studentRepository.save(student);
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating student: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // VIEW all students
    @GetMapping("/all")
    public ResponseEntity<List<StudentData>> getAllStudents() {
        try {
            List<StudentData> students = studentRepository.findAll();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching students: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get students by department and section
@GetMapping("/dept/{department}/section/{section}")
public ResponseEntity<List<StudentData>> getStudentsByDeptAndSection(
        @PathVariable String department,
        @PathVariable String section) {
    try {
        List<StudentData> students = studentRepository.findByDepartmentAndSection(department, section);
        return new ResponseEntity<>(students, HttpStatus.OK);
    } catch (Exception e) {
        logger.error("Error fetching students by dept and section: {}", e.getMessage());
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


    // UPDATE student details first time
    @PutMapping("/userid/{studentid}")
    public ResponseEntity<StudentData> updateStudentFirstTime(
            @PathVariable String studentid, 
            @RequestBody StudentData studentData) {
        Optional<StudentData> existingStudentOptional = studentRepository.findByStudentid(studentid);

        if (existingStudentOptional.isPresent()) {
            StudentData updatedStudent = existingStudentOptional.get();

            // Update all fields
            updatedStudent.setEcetrank(studentData.getEcetrank());
            updatedStudent.setEamcetrank(studentData.getEamcetrank());
            updatedStudent.setFathername(studentData.getFathername());
            updatedStudent.setMothername(studentData.getMothername());
            updatedStudent.setFathernumber(studentData.getFathernumber());
            updatedStudent.setBloodgroup(studentData.getBloodgroup());
            updatedStudent.setAddress(studentData.getAddress());
            updatedStudent.setPhoneno(studentData.getPhoneno());
            updatedStudent.setEmail(studentData.getEmail());

            StudentData savedStudent = studentRepository.save(updatedStudent);
            return new ResponseEntity<>(savedStudent, HttpStatus.OK);
        } else {
            logger.warn("Student with studentid {} not found", studentid);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/points/{studentid}")
public ResponseEntity<StudentData> updateStudentPoints(
        @PathVariable String studentid, 
        @RequestBody Map<String, Integer> pointsUpdate) {
    Optional<StudentData> existingStudentOptional = studentRepository.findByStudentid(studentid);

    if (existingStudentOptional.isPresent()) {
        StudentData student = existingStudentOptional.get();
        
        // Get current points (default to 0 if null)
        int currentPoints = student.getPoints();
        
        // Get points to add from request (default to 0 if not provided)
        int pointsToAdd = pointsUpdate.getOrDefault("points", 0);
        
        // Calculate new total points
        int newPoints = currentPoints + pointsToAdd;
        
        // Update only the points field
        student.setPoints(newPoints);
        
        StudentData savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(savedStudent);
    } else {
        logger.warn("Student with studentid {} not found", studentid);
        return ResponseEntity.notFound().build();
    }
}



    // GET student by ID
    @GetMapping("/userid/{id}")
    public ResponseEntity<StudentData> getStudentById(@PathVariable String id) {
        Optional<StudentData> student = studentRepository.findByStudentid(id);

        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            logger.warn("Student with ID {} not found", id);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // DELETE student by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable String id) {
        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting student: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}