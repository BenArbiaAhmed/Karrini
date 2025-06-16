package com.karrini.Karrini.service;


import com.karrini.Karrini.controller.UnenrollmentController;
import com.karrini.Karrini.exception.CourseNotFoundException;
import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.Enrollment;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.repository.EnrollmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class UnenrollmentService {


    private static final Logger log = LoggerFactory.getLogger(UnenrollmentService.class);
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    public UnenrollmentService(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }


    @Transactional
    public boolean unenroll(Long courseId, String email) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new CourseNotFoundException("Course not found for unenrollment.");
        }

        try {
            int deletedCount = enrollmentRepository.deleteByLearner_EmailAndCourse_Id(email, courseId);
            if (deletedCount == 0) {
                log.warn("No enrollment found for courseId={} and email={}", courseId, email);
                return false;
            }

            Course c = course.get();
            c.setLearnerCount(c.getLearnerCount() - 1);
            courseRepository.save(c);
            return true;

        } catch (RuntimeException e) {
            log.error("Unenrollment failed for courseId={} and email={}", courseId, email, e);
            return false;
        }
    }
}
