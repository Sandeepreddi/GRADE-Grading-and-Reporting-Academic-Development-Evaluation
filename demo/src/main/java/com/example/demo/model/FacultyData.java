package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "FacultyData")
public class FacultyData {

    @Id
    private String id;
    private String name;
    private String userid;
    private String department;
    private String profession;
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
    private List<String> booksPublished;

    // Default constructor
    public FacultyData() {}

    // Constructor with only id, name, userid, and password
    public FacultyData(String id, String name, String userid, String password, String department) {
        this.id = id;
        this.name = name;
        this.userid = userid;
        this.password = password;
        this.department = department;
    }

    // Constructor with profession, address, phoneno, and email
    public FacultyData(String profession, String address, String phoneno, String email) {
        this.profession = profession;
        this.address = address;
        this.phoneno = phoneno;
        this.email = email;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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

    public List<String> getBooksPublished() {
        return booksPublished;
    }

    public void setBooksPublished(List<String> booksPublished) {
        this.booksPublished = booksPublished;
    }
}
