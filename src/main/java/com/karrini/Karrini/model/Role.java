package com.karrini.Karrini.model;

public enum Role {
    USER,
    INSTRUCTOR,
    LEARNER,
    ADMIN;

    public String getName() {
        return name();
    }
}

