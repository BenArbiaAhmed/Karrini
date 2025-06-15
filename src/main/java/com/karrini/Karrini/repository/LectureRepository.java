package com.karrini.Karrini.repository;

import com.karrini.Karrini.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture getLectureById(Long id);
    Lecture getLectureByCourse_IdAndDisplayOrder(Long courseId, Integer displayOrder);
}
