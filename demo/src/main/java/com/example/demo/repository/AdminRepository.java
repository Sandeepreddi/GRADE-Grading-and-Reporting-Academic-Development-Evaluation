package com.example.demo.repository;

import com.example.demo.model.Admindata;
import com.example.demo.model.Residentdata;
import com.example.demo.model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admindata, String> {

    Optional<Admindata> findByEmail(String email);
    
}