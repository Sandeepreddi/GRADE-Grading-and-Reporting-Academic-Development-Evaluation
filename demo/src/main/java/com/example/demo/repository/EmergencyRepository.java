package com.example.demo.repository;


import com.example.demo.model.EmergencyContacts;
import com.example.demo.model.Residentdata;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyRepository extends MongoRepository<EmergencyContacts, String> {

    Optional<EmergencyContacts> findById(String id);
    
}
