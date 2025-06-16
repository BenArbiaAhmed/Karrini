package com.karrini.Karrini.service;


import com.karrini.Karrini.exception.LeanerAlreadyEnrolledException;
import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.Enrollment;
import com.karrini.Karrini.model.Learner;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.repository.EnrollmentRepository;
import com.karrini.Karrini.repository.LearnerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class EnrollmentService {

    private final CourseRepository courseRepository;
    private final LearnerRepository learnerRepository;
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(CourseRepository courseRepository, LearnerRepository learnerRepository, EnrollmentRepository enrollmentRepository){
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.learnerRepository = learnerRepository;
    }
    @Transactional
    public Enrollment enrollUserInCourse(String email, Long courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        Learner learner = learnerRepository.findByEmail(email);
        if (enrollmentRepository.findByLearnerAndCourse(learner, course).isPresent()){
            throw new LeanerAlreadyEnrolledException("You are already enrolled.");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setLearner(learner);
        enrollment.setEnrollmentDate(LocalDateTime.now());
        course.setLearnerCount(course.getLearnerCount() + 1);
        courseRepository.save(course);
        Set<Enrollment> enrollments = learner.getEnrollments();
        enrollments.add(enrollment);
        learner.setEnrollments(enrollments);
        return enrollmentRepository.save(enrollment);
    }
}
