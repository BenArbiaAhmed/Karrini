package com.karrini.Karrini.repository;

import com.karrini.Karrini.model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {
    Learner findById(long id);
    Learner findByUsername(String username);
    Learner findByEmail(String email);
}
