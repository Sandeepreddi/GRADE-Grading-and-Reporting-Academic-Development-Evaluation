package com.example.demo.repository;

import com.example.demo.model.Residentdata;
import com.example.demo.model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResidentRepository extends MongoRepository<Residentdata, String> {
    Optional<Residentdata> findByEmail(String email);
    
}