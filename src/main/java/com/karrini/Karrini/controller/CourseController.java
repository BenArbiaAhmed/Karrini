package com.karrini.Karrini.controller;


import com.karrini.Karrini.dto.CourseContentDto;
import com.karrini.Karrini.model.LearningMaterial;
import com.karrini.Karrini.model.TextMaterial;
import com.karrini.Karrini.model.VideoMaterial;
import com.karrini.Karrini.service.CourseService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/learn")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/course/{courseId}/lecture/{displayOrder}")
    public String learn(@PathVariable Long courseId,
                        @PathVariable Integer displayOrder,
                        @AuthenticationPrincipal UserDetails userDetails,
                        Model model) {
        CourseContentDto content = courseService.getCourseContent(courseId, displayOrder, userDetails);

        model.addAttribute("course", content.getCourse());
        model.addAttribute("lectures", content.getLectures());
        LearningMaterial material = content.getMaterial();
        if (material instanceof VideoMaterial video) {
            model.addAttribute("videoUrl", video.getVideoUrl());
        } else if (material instanceof TextMaterial text) {
            model.addAttribute("textContent", text.getContent());
        }
        model.addAttribute("materialType", content.getMaterialType().toString());

        return "course-content";
    }
}
