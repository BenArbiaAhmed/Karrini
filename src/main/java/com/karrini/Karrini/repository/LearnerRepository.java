package com.karrini.Karrini.repository;

import com.karrini.Karrini.model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearnerRepository extends JpaRepository<Learner, Long> {
    Learner findById(long id);
}
