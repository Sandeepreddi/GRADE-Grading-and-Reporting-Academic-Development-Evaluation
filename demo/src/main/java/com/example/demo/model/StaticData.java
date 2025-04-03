package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "StaticData") // MongoDB collection name
public class StaticData {
    @Id
    private String id; 
    private int blocks;

    public StaticData() {}

    public StaticData(int blocks)
    {
        this.blocks = blocks;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    
}
