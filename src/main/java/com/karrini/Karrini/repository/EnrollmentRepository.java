package com.karrini.Karrini.repository;

import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.Enrollment;
import com.karrini.Karrini.model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Enrollment findById(long id);
    Optional<Enrollment> findByLearnerAndCourse(Learner learner, Course course);
}
