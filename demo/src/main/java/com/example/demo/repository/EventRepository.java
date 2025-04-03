package com.example.demo.repository;

import com.example.demo.model.Eventdata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Eventdata, String> {
    // Basic CRUD operations are automatically provided by MongoRepository
    // You can add custom query methods here if needed
}