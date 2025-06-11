package com.karrini.Karrini.controller;


import com.karrini.Karrini.dto.EnrollmentRequest;
import com.karrini.Karrini.model.Enrollment;
import com.karrini.Karrini.repository.EnrollmentRepository;
import com.karrini.Karrini.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class EnrollmentController {


    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enroll")
    public String enroll(@RequestBody EnrollmentRequest enrollmentRequest, @AuthenticationPrincipal UserDetails userDetails){
        Long courseId = enrollmentRequest.getCourseId();
        String username = userDetails.getUsername();
        try {
            Enrollment enrollment = enrollmentService.enrollUserInCourse(username, courseId);
            return "redirect:/learn/course/" + courseId;
        }
        catch (RuntimeException e){
            return "redirect:/";
        }
    }
}
