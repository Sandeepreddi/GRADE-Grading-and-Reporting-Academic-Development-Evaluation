package com.example.demo.repository;

import com.example.demo.model.InternshipsData;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface InternshipRepository extends MongoRepository<InternshipsData, String> {
    List<InternshipsData> findByStudentId(String studentId);
    List<InternshipsData> findByDepartment(String department);
    List<InternshipsData> findByStatusAndVerifiedBy(String status, String verifiedBy);

}
