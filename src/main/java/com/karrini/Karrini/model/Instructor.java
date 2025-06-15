package com.karrini.Karrini.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Instructor extends User{
    private String facebookUrl;
    private String xUrl;
    private String instagramUrl;
    @OneToMany(
            mappedBy = "instructor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List <Course> courses = new ArrayList<>();

    public Instructor() {
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }
    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }
    public String getxUrl() {
        return xUrl;
    }
    public void setxUrl(String xUrl) {
        this.xUrl = xUrl;
    }
    public String getInstagramUrl() {
        return instagramUrl;
    }
    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
