package com.example.demo.repository;

import com.example.demo.model.StudentData;  // <-- Replace with your actual Student model
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

// Interface to handle Student data operations
public interface StudentRepository extends MongoRepository<StudentData, String> {
    Optional<StudentData> findByStudentid(String studentid);  // Changed to lowercase 'i'
    List<StudentData> findByDepartmentAndSection(String department, String section);
}
