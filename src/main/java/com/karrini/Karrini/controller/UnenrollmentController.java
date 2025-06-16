package com.karrini.Karrini.controller;


import com.karrini.Karrini.exception.CourseNotFoundException;
import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.service.UnenrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class UnenrollmentController {

    private final CourseRepository courseRepository;
    private final UnenrollmentService unenrollmentService;

    public UnenrollmentController(CourseRepository courseRepository, UnenrollmentService unenrollmentService){
        this.courseRepository = courseRepository;
        this.unenrollmentService = unenrollmentService;
    }

    @PostMapping("/unenroll/{courseId}")
    public ResponseEntity<String> unenrollFromCourse(@PathVariable Long courseId, @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        boolean success = unenrollmentService.unenroll(courseId, email);
        System.out.println("Unenroll called for courseId=" + courseId + ", user=" + userDetails.getUsername());
        if (success) {
            return ResponseEntity.ok("Unenrolled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unenrollment failed");
        }
    }
}
