package com.karrini.Karrini.repository;

import java.util.List;

import com.karrini.Karrini.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);
    Course findById(long id);
}