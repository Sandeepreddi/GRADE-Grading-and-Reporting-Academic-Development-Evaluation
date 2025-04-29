package com.example.demo.repository;
 
import com.example.demo.model.DepartmentsData;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
 
public interface DepartmentsRepository extends MongoRepository<DepartmentsData, String> {
    Optional<DepartmentsData> findById(String id);
}