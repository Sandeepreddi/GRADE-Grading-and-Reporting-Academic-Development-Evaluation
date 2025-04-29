package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AdminData")  // MongoDB Collection name
public class AdminData {
    @Id
    private String id;
    private String name;
    private String userid;
    private String phoneno;
    private String email;
    private String profession;
    private String address;

    // Default constructor
    public AdminData() {}

    // Constructor with all fields
    public AdminData(String name, String userid, String phoneno, String email, String profession, String address) {
        this.name = name;
        this.userid = userid;
        this.phoneno = phoneno;
        this.email = email;
        this.profession = profession;
        this.address = address;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String mailid) {
        this.email = mailid;
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
}
