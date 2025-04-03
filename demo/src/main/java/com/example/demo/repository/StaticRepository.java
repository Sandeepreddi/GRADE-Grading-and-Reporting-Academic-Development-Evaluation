package com.example.demo.repository;

import com.example.demo.model.StaticData;
import com.example.demo.model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StaticRepository extends MongoRepository<StaticData, String> {
    Optional<StaticData> findById(String id);
    
}