package com.karrini.Karrini.repository;

import com.karrini.Karrini.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Enrollment findById(long id);
}
