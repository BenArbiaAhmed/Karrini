package com.karrini.Karrini.service;


import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.Enrollment;
import com.karrini.Karrini.model.Learner;
import com.karrini.Karrini.model.User;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.repository.EnrollmentRepository;
import com.karrini.Karrini.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class EnrollmentService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Transactional
    public Enrollment enrollUserInCourse(String username, Long courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        User user = userRepository.findByUsername(username);
        if (enrollmentRepository.findByLearnerAndCourse((Learner) user, course).isPresent()){
            throw new RuntimeException("User is already enrolled in this course.");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setLearner((Learner)user);
        enrollment.setEnrollmentDate(LocalDateTime.now());
        course.setLearnerCount(course.getLearnerCount() + 1);
        Set<Enrollment> enrollments = ((Learner) user).getEnrollments();
        enrollments.add(enrollment);
        ((Learner) user).setEnrollments(enrollments);
        return enrollmentRepository.save(enrollment);
    }
}
