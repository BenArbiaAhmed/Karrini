package com.karrini.Karrini.controller;


import com.karrini.Karrini.exception.CourseNotFoundException;
import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.CourseStatus;
import com.karrini.Karrini.repository.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {


    private final CourseRepository courseRepository;
    public AdminController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @PatchMapping("/admin/accept/{courseId}")
    public ResponseEntity<String> accept(@PathVariable Long courseId){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with id: " + courseId + " was not found."));
        course.setCourseStatus(CourseStatus.ACCEPTED);
        courseRepository.save(course);
        return ResponseEntity.ok("Course was accepted");
    }
    @PatchMapping("/admin/reject/{courseId}")
    public ResponseEntity<String> reject(@PathVariable Long courseId){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with id: " + courseId + " was not found."));
        course.setCourseStatus(CourseStatus.REJECTED);
        courseRepository.save(course);
        return ResponseEntity.ok("Course was rejected");
    }
}
