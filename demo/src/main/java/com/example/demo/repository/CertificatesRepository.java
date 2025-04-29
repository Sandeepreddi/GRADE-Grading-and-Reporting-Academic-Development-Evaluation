package com.example.demo.repository;
 
import com.example.demo.model.InternshipsData;

import java.util.Optional;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CertificatesRepository extends MongoRepository<InternshipsData, String> {

    List<InternshipsData> findByStudentId(String studentId);
    
}
