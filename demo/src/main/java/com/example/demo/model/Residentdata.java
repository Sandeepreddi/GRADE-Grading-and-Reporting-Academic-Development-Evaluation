package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ResidentData") // MongoDB collection name
public class Residentdata {
    @Id
    private String id; // Unique identifier for the document
    private String name; // Resident's name
    private String phone_number; // Resident's phone number (changed from long to String)
    private String societyName; // Name of the society
    private String flatNo; // Flat number (renamed from flat_No to flatNo)
    private String postal;
    private String email;

    // Default Constructor (required for Spring Data MongoDB)
    public Residentdata() {}

    // Parameterized Constructor
    public Residentdata(String id, String name, String phone_number, String societyName, String flatNo, String postal,String email) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.societyName = societyName;
        this.flatNo = flatNo;
        this.postal = postal;
        this.email=email;
    }

    // Getter and Setter methods

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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}