package com.example.demo.repository;

import com.example.demo.model.AttendanceRecord;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttendanceRepository extends MongoRepository<AttendanceRecord, String> {

   // List<AttendanceRecord> findByStudentId(String studentId);
    
}
