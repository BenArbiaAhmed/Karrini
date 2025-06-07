package com.karrini.Karrini.repository;

import com.karrini.Karrini.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture getLectureById(Long id);
}
