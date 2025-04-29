package com.example.demo.controller;

import com.example.demo.model.AttendanceRecord;
import com.example.demo.repository.AttendanceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;

    public AttendanceController(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @PostMapping
    public ResponseEntity<AttendanceRecord> saveAttendance(@RequestBody AttendanceRecord record) {
        try {
            AttendanceRecord savedRecord = attendanceRepository.save(record);
            return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // New GET endpoint to fetch all attendance records
    @GetMapping
    public ResponseEntity<List<AttendanceRecord>> getAllAttendanceRecords() {
        try {
            List<AttendanceRecord> records = attendanceRepository.findAll();
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
