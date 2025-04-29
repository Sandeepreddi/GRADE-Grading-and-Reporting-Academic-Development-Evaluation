package com.example.demo.controller;

import com.example.demo.model.NoticeModel;
import com.example.demo.repository.NoticeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notice")
@CrossOrigin(origins = "http://localhost:3000")
public class NoticeController {

    private final NoticeRepository noticeRepository;
    private final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    public NoticeController(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    // ✅ Create Notice
    @PostMapping("/create")
    public ResponseEntity<NoticeModel> createNotice(@RequestBody NoticeModel notice) {
        try {
            NoticeModel savedNotice = noticeRepository.save(notice);
            return new ResponseEntity<>(savedNotice, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating notice: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Get All Notices
    @GetMapping("/all")
    public ResponseEntity<List<NoticeModel>> getAllNotices() {
        try {
            List<NoticeModel> notices = noticeRepository.findAll();
            return new ResponseEntity<>(notices, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching notices: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Delete Notice by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteNotice(@PathVariable String id) {
        try {
            noticeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting notice with id " + id + ": ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
