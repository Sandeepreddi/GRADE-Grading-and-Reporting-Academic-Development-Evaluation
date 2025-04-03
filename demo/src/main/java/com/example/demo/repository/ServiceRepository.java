package com.example.demo.repository;

import com.example.demo.model.Servicedata;
import com.example.demo.model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceRepository extends MongoRepository<Servicedata, String> {
    Optional<Servicedata> findById(String id);
    
}