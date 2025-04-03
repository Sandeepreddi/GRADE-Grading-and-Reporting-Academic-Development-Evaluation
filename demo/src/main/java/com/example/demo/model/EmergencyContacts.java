package com.example.demo.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EmergencyContacts")
public class EmergencyContacts {
    @Id
    private String id;
    private String name;
    private String phoneNumber;

    // Default constructor
    public EmergencyContacts() {}

    // Parameterized constructor
    public EmergencyContacts(String name, String phoneNumber) {
       
        this.name = name;
        this.phoneNumber = phoneNumber;
    }


    public String getId() {
        return id;
    }

    // Setter for name
    public void setId(String id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter for phoneNumber
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
