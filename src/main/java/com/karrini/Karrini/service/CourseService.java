package com.karrini.Karrini.service;

import com.karrini.Karrini.dto.CourseContentDto;
import com.karrini.Karrini.exception.CourseNotFoundException;
import com.karrini.Karrini.exception.LectureNotFoundException;
import com.karrini.Karrini.exception.MaterialNotFoundException;
import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.LearningMaterial;
import com.karrini.Karrini.model.Lecture;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.repository.EnrollmentRepository;
import com.karrini.Karrini.repository.LectureRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(CourseRepository courseRepository, LectureRepository lectureRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.lectureRepository = lectureRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public CourseContentDto getCourseContent(Long courseId, Integer displayOrder, UserDetails userDetails) {
        boolean isEnrolled = enrollmentRepository.existsByCourse_IdAndLearner_Email(courseId, userDetails.getUsername());
        if(isEnrolled){
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new CourseNotFoundException("Course with id: " + courseId + " not found"));

            Lecture lecture = lectureRepository.findByCourse_IdAndDisplayOrder(courseId, displayOrder)
                    .orElseThrow(() -> new LectureNotFoundException("Lecture with display order: " + displayOrder + " not found"));

            LearningMaterial material = lecture.getMaterial();
            if (material == null) {
                throw new MaterialNotFoundException("Material for lecture not found");
            }
            return new CourseContentDto(course, course.getLectures(), material, material.getMaterialType());
        } else {
            throw new AccessDeniedException("Learner is not enrolled in the course");
        }
    }
}
