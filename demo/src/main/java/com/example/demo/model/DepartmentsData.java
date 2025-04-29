package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "DepartmentsData")
public class DepartmentsData {

    @Id
    private String id;
    private String Department_Name;
    private int Sections;
    private List<List<List<Subject>>> subjects; // 4 years -> 2 semesters -> list of subjects

    // Default constructor
    public DepartmentsData() {
        // Initialize with 4 years, each with 2 semesters
        subjects = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<List<Subject>> year = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                year.add(new ArrayList<>()); // each semester has an empty subject list
            }
            subjects.add(year);
        }
    }

    // Parameterized constructor
    public DepartmentsData(String id, String department_Name, int sections, List<List<List<Subject>>> subjects) {
        this.id = id;
        Department_Name = department_Name;
        Sections = sections;
        this.subjects = subjects;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment_Name() {
        return Department_Name;
    }

    public void setDepartment_Name(String department_Name) {
        Department_Name = department_Name;
    }

    public int getSections() {
        return Sections;
    }

    public void setSections(int sections) {
        Sections = sections;
    }

    public List<List<List<Subject>>> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<List<List<Subject>>> subjects) {
        this.subjects = subjects;
    }

    // Inner class for Subject
    public static class Subject {
        private String subjectName;
        private String subjectCode;

        public Subject() {}

        public Subject(String subjectName, String subjectCode) {
            this.subjectName = subjectName;
            this.subjectCode = subjectCode;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getSubjectCode() {
            return subjectCode;
        }

        public void setSubjectCode(String subjectCode) {
            this.subjectCode = subjectCode;
        }
    }
}
