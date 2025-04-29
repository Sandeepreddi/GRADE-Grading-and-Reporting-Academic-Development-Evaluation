package com.example.demo.repository;
 
import com.example.demo.model.UserData;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
 
public interface UserRepository extends MongoRepository<UserData, String> {
    Optional<UserData> findByEmail(String email);
}