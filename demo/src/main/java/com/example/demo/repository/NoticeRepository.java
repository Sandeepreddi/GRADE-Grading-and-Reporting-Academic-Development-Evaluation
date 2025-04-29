package com.example.demo.repository;

import com.example.demo.model.NoticeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface NoticeRepository extends MongoRepository<NoticeModel, String> {
    Optional<NoticeModel> findByid(String id);
}

