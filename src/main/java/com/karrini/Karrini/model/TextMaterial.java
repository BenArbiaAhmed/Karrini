package com.karrini.Karrini.model;


import jakarta.persistence.Entity;

@Entity
public class TextMaterial extends LearningMaterial{
    private String uri;
    public TextMaterial(){

    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
