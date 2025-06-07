package com.karrini.Karrini.model;


import jakarta.persistence.Entity;

@Entity
public class VideoMaterial extends LearningMaterial{
    private String videoUrl;
    public VideoMaterial(){

    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
