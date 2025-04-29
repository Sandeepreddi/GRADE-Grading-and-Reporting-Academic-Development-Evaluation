package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "attendance_records")
public class AttendanceRecord {

    @Id
    private String id;

    private String date;
    private String department;
    private String section;
    private String semester;

    // Map<studentId, Map<subjectCode, status>>
    private Map<String, Map<String, String>> records;

    public AttendanceRecord() {}

    public AttendanceRecord(String date, String department, String section, String semester, Map<String, Map<String, String>> records) {
        this.date = date;
        this.department = department;
        this.section = section;
        this.semester = semester;
        this.records = records;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Map<String, Map<String, String>> getRecords() {
        return records;
    }

    public void setRecords(Map<String, Map<String, String>> records) {
        this.records = records;
    }
}
