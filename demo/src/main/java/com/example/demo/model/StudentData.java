package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "StudentData")
public class StudentData {

    @Id
    private String id;
    private String name;
    private String studentid;
    private String department;
    private String section;
    private String ecetrank;
    private String eamcetrank;
    private String fathername;
    private String mothername;
    private String fathernumber;
    private String bloodgroup;
    private String address;
    private String phoneno;
    private String email;
    private String profile;
    private String password;
    private int points;

    private List<String> certifications;
    private List<String> achievements;
    private List<String> rewardsAndAwards;
    private List<String> papersPublished;

    // Default constructor
    public StudentData() {}

    // Constructor with id, name, studentid, password, department
    public StudentData(String id, String name, String studentid, String password, String department) {
        this.id = id;
        this.name = name;
        this.studentid = studentid;
        this.password = password;
        this.department = department;
    }

    // Constructor with address, phoneno, email
    public StudentData(String address, String phoneno, String email) {
        this.address = address;
        this.phoneno = phoneno;
        this.email = email;
    }

    // Full details constructor (excluding lists)
    public StudentData(String name, String studentid, String department, String section,
                       String ecetrank, String eamcetrank, String fathername, String mothername,
                       String fathernumber, String bloodgroup, String address, String phoneno,
                       String email, String profile, String password, int points) {
        this.name = name;
        this.studentid = studentid;
        this.department = department;
        this.section = section;
        this.ecetrank = ecetrank;
        this.eamcetrank = eamcetrank;
        this.fathername = fathername;
        this.mothername = mothername;
        this.fathernumber = fathernumber;
        this.bloodgroup = bloodgroup;
        this.address = address;
        this.phoneno = phoneno;
        this.email = email;
        this.profile = profile;
        this.password = password;
        this.points = points;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
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

    public String getEcetrank() {
        return ecetrank;
    }

    public void setEcetrank(String ecetrank) {
        this.ecetrank = ecetrank;
    }

    public String getEamcetrank() {
        return eamcetrank;
    }

    public void setEamcetrank(String eamcetrank) {
        this.eamcetrank = eamcetrank;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getFathernumber() {
        return fathernumber;
    }

    public void setFathernumber(String fathernumber) {
        this.fathernumber = fathernumber;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }

    public List<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<String> achievements) {
        this.achievements = achievements;
    }

    public List<String> getRewardsAndAwards() {
        return rewardsAndAwards;
    }

    public void setRewardsAndAwards(List<String> rewardsAndAwards) {
        this.rewardsAndAwards = rewardsAndAwards;
    }

    public List<String> getPapersPublished() {
        return papersPublished;
    }

    public void setPapersPublished(List<String> papersPublished) {
        this.papersPublished = papersPublished;
    }
}
