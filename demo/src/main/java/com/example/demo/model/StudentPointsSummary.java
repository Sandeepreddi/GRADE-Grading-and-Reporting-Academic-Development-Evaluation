package com.example.demo.model;

public class StudentPointsSummary {
    private String studentId;
    private int internshipPoints;
    private int projectPoints;
    private int certificatePoints;
    private int totalPoints;

    // Constructor
    public StudentPointsSummary(String studentId, int internshipPoints, int projectPoints, int certificatePoints, int totalPoints) {
        this.studentId = studentId;
        this.internshipPoints = internshipPoints;
        this.projectPoints = projectPoints;
        this.certificatePoints = certificatePoints;
        this.totalPoints = totalPoints;
    }

    // Getters and setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getInternshipPoints() {
        return internshipPoints;
    }

    public void setInternshipPoints(int internshipPoints) {
        this.internshipPoints = internshipPoints;
    }

    public int getProjectPoints() {
        return projectPoints;
    }

    public void setProjectPoints(int projectPoints) {
        this.projectPoints = projectPoints;
    }

    public int getCertificatePoints() {
        return certificatePoints;
    }

    public void setCertificatePoints(int certificatePoints) {
        this.certificatePoints = certificatePoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}

