package com.karrini.Karrini.model;


import jakarta.persistence.Entity;

@Entity
public class TextMaterial extends LearningMaterial{
    private String content;
    public TextMaterial(){

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
