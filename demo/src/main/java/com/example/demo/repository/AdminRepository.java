package com.example.demo.repository;
 
import com.example.demo.model.AdminData;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
 
public interface AdminRepository extends MongoRepository<AdminData, String> {
    Optional<AdminData> findByEmail(String email);
    Optional<AdminData> findByUserid(String userid);
}