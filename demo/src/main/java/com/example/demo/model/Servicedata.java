package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Servicedata") // MongoDB collection name
public class Servicedata {
    @Id
    private String id; // Unique identifier for the document
    private String name; // Resident's name
    private String address; // Resident's address
    private String phoneNumber; // Resident's phone number (changed from long to String)
    private String additionalNotes;
    private String service;

    // Default constructor
    public Servicedata() {
    }

    // Parameterized constructor
    public Servicedata(String name, String address, String phoneNumber, String additionalNotes, String service) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.additionalNotes = additionalNotes;
        this.service = service;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}