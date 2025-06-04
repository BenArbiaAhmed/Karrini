package com.karrini.Karrini.model;

import jakarta.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;
    private String duration;
    private Double price;
    private Integer learnerCount = 0;
    private Integer reviewCount = 0;
    private Integer starsCount = 0;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Course() {
    }

    public Course(Long id, String name, String description, User instructor, String duration, Double price, Integer learnerCount, Integer reviewCount, Integer starsCount, String imageUrl, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.instructor = instructor;
        this.duration = duration;
        this.price = price;
        this.learnerCount = learnerCount;
        this.reviewCount = reviewCount;
        this.starsCount = starsCount;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLearnerCount() {
        return learnerCount;
    }

    public void setLearnerCount(Integer learnerCount) {
        this.learnerCount = learnerCount;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Integer getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(Integer starsCount) {
        this.starsCount = starsCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}