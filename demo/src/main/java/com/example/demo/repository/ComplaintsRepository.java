package com.example.demo.repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Complaints;

@Repository
public interface ComplaintsRepository extends MongoRepository<Complaints, String>  {
    Optional<Complaints> findById(String id);
    
}
