package com.karrini.Karrini.controller;


import com.karrini.Karrini.exception.LeanerAlreadyEnrolledException;
import com.karrini.Karrini.model.Enrollment;
import com.karrini.Karrini.service.EnrollmentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EnrollmentController {


    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enroll")
    public RedirectView enroll(@RequestParam Long courseId, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes attributes){
        String email = userDetails.getUsername();
        try {
            Enrollment enrollment = enrollmentService.enrollUserInCourse(email, courseId);
            return new RedirectView("/learn/course/" + courseId + "/lecture/1");
        } catch (LeanerAlreadyEnrolledException laee){
            attributes.addFlashAttribute("errorMessage", "You are already enrolled.");
            return new RedirectView("/");
        }
        catch (RuntimeException e){
            attributes.addFlashAttribute("errorMessage", "Enrollment Failed");
            return new RedirectView("/");
        }
    }
}
