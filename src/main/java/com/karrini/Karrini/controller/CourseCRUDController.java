package com.karrini.Karrini.controller;


import com.karrini.Karrini.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseCRUDController {

    private final CourseService courseService;

    public CourseCRUDController(CourseService courseService){
        this.courseService = courseService;
    }


    @PostMapping("/delete/{courseId}")
    public ResponseEntity<String> delete(@PathVariable Long courseId){
        boolean success = courseService.delete(courseId);
        System.out.println("Deletion called for courseId=" + courseId);
        if (success) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deletion failed");
        }
    }
}
