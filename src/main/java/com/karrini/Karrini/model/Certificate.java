package com.karrini.Karrini.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Learner learner;
    @ManyToOne
    private Instructor instructor;
    private LocalDate issueDate;

    public Certificate() {
    }

    public Certificate(Long id, Learner learner, Instructor instructor, LocalDate issueDate) {
        this.id = id;
        this.learner = learner;
        this.instructor = instructor;
        this.issueDate = issueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Learner getLearner() {
        return learner;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
}
