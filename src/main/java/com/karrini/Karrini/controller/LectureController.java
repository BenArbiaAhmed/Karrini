package com.karrini.Karrini.controller;

import com.karrini.Karrini.dto.LectureDto;

import com.karrini.Karrini.service.LectureService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
public class LectureController {


    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping("addLecture/course/{courseId}")
    public ResponseEntity<String> addLecture(@PathVariable Long courseId, @ModelAttribute LectureDto lectureDto, @AuthenticationPrincipal UserDetails userDetails) {
        return lectureService.saveNewLecture(courseId, lectureDto, userDetails);
    }
}
