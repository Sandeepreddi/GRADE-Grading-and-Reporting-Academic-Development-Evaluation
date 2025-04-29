package com.example.demo.repository;

import com.example.demo.model.FacultyData;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface FacultyRepository extends MongoRepository<FacultyData, String> {
    Optional<FacultyData> findByUserid(String id);
}

