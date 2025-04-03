package com.example.demo.repository;

import com.example.demo.model.NoticeData;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface NoticeRepository extends MongoRepository<NoticeData, String> {
    Optional<NoticeData> findById(String id);
    
}
