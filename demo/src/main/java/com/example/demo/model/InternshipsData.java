package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "InternshipData")
public class InternshipsData {
    @Id
    private String id;
    private String name;
    private String studentId;
    private String department;
    private String type;  
    private String title;
    private String company;
    private String duration;
    private String status = "PENDING"; // PENDING, APPROVED, REJECTED
    private String certificateUrl;
    private String verifiedBy;
    private String githuburl;
    private String liveprojecturl;
    private String field;
    private String guidename;
    private String abstracturl;
    private String Documentationurl;
    private Integer points;

    // No-args constructor
    public InternshipsData() {}

    // In InternshipsData.java

// Update all constructors to include type parameter
// Internship constructor
public InternshipsData(String id, String name, String studentId, String department, String type, 
String title, String company, String duration, String status, 
String certificateUrl, String githuburl, String liveprojecturl, 
String field, Integer points) {
this.id = id;
this.name = name;
this.studentId = studentId;
this.department = department;
this.type = type;
this.title = title;
this.company = company;
this.duration = duration;
this.status = status;
this.certificateUrl = certificateUrl;
this.githuburl = githuburl;
this.liveprojecturl = liveprojecturl;
this.field = field;
this.points=points;
}

// Certification constructor
public InternshipsData(String id, String name, String studentId, String department, 
String type, String title, String company, String field, 
String duration, String status, String certificateUrl,Integer points) {
this.id = id;
this.name = name;
this.studentId = studentId;
this.department = department;
this.type = type;
this.title = title;
this.company = company;
this.field = field;
this.duration = duration;
this.status = status;
this.certificateUrl = certificateUrl;
this.points=points;
}

// Project constructor
public InternshipsData(String id, String name, String studentId, String department, 
String type, String title, String duration, String status, 
String guidename, String abstracturl, String documentationurl,
String liveprojecturl, String githuburl, String field,Integer points) {
this.id = id;
this.name = name;
this.studentId = studentId;
this.department = department;
this.type = type;
this.title = title;
this.duration = duration;
this.status = status;
this.guidename = guidename;
this.abstracturl = abstracturl;
this.Documentationurl = documentationurl;
this.liveprojecturl = liveprojecturl;
this.githuburl = githuburl;
this.field = field;
this.points=points;
}

    // Getters and Setters (unchanged)

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getStudentId() { return studentId; }

    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getDepartment() { return department; }

    public void setDepartment(String department) { this.department = department; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getCompany() { return company; }

    public void setCompany(String company) { this.company = company; }

    public String getDuration() { return duration; }

    public void setDuration(String duration) { this.duration = duration; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getCertificateUrl() { return certificateUrl; }

    public void setCertificateUrl(String certificateUrl) { this.certificateUrl = certificateUrl; }

    public String getVerifiedBy() { return verifiedBy; }

    public void setVerifiedBy(String verifiedBy) { this.verifiedBy = verifiedBy; }

    public String getGithuburl() { return githuburl; }

    public void setGithuburl(String githuburl) { this.githuburl = githuburl; }

    public String getLiveprojecturl() { return liveprojecturl; }

    public void setLiveprojecturl(String liveprojecturl) { this.liveprojecturl = liveprojecturl; }

    public String getField() { return field; }

    public void setField(String field) { this.field = field; }

    public String getGuidename() { return guidename; }

    public void setGuidename(String guidename) { this.guidename = guidename; }

    public String getAbstracturl() { return abstracturl; }

    public void setAbstracturl(String abstracturl) { this.abstracturl = abstracturl; }

    public String getDocumentationurl() { return Documentationurl; }

    public void setDocumentationurl(String documentationurl) { this.Documentationurl = documentationurl; }

    public Integer getPoints() { return points; }

    public void setPoints(Integer points) { this.points = points; }
}
