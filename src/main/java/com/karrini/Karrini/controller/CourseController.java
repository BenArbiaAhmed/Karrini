package com.karrini.Karrini.controller;


import com.karrini.Karrini.exception.CourseNotFoundException;
import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.LearningMaterial;
import com.karrini.Karrini.model.Lecture;
import com.karrini.Karrini.model.MaterialType;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.repository.LectureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {

    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;

    public CourseController(CourseRepository courseRepository, LectureRepository lectureRepository) {
        this.courseRepository = courseRepository;
        this.lectureRepository = lectureRepository;
    }

    @GetMapping("/learn/course/{courseId}/lecture/{displayOrder}")
    public String learn(@PathVariable Long courseId, @PathVariable Integer displayOrder, Model model) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            model.addAttribute("course", course.get());
            Course courseObj = course.get();
            List<Lecture> lectures = courseObj.getLectures();
            Lecture currentLecture = lectureRepository.getLectureByCourse_IdAndDisplayOrder(courseId, displayOrder);
            model.addAttribute("lectures", lectures);
            LearningMaterial learningMaterial = currentLecture.getMaterial();
            if (learningMaterial.getMaterialType() == MaterialType.TEXT) {
                model.addAttribute("materialType", "text");
            } else if (learningMaterial.getMaterialType() == MaterialType.VIDEO) {
                model.addAttribute("materialType", "video");
            } else if (learningMaterial.getMaterialType() == MaterialType.QUIZ) {
                model.addAttribute("materialType", "quiz");
            }
            model.addAttribute("material", learningMaterial);
            return "course-content";
        }
        else {
            throw new CourseNotFoundException("Course with id: " + courseId + " is not found.");
        }
    }
}
