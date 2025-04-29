package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "NoticeData")
public class NoticeModel {
    @Id
    private String id;
    private String title;
    private String date;
    private String content;
    private String role;

    // No-args constructor
    public NoticeModel() {
    }

    // All-args constructor
    public NoticeModel(String id, String title, String date, String content, String role) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
        this.role = role;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
