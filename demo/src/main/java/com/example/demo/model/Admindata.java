package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AdminData")  // MongoDB Collection name
public class Admindata {
    @Id
    private String id;
    private String name;
    private String phone_number;
    private String societyName;
    private String societyAddress;
    private String city;
    private String district;
    private String postal;
    private String email;

    // Default Constructor
    public Admindata() {}

    // Parameterized Constructor
    public Admindata(String id, String name, String phone_number, String societyName, String societyAddress,
                     String city, String district, String postal,String email) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.societyName = societyName;
        this.societyAddress = societyAddress;
        this.city = city;
        this.district = district;
        this.postal = postal;
        this.email=email;
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

    public String getSocietyAddress() {
        return societyAddress;
    }

    public void setSocietyAddress(String societyAddress) {
        this.societyAddress = societyAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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