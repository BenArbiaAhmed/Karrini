package com.karrini.Karrini.repository;

import com.karrini.Karrini.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture getLectureById(Long id);
    Lecture getLectureByCourse_IdAndDisplayOrder(Long courseId, Integer displayOrder);
    Optional<Lecture> findByCourse_IdAndDisplayOrder(Long courseId, Integer displayOrder);
    @Query("SELECT MAX(l.displayOrder) FROM Lecture l WHERE l.course.id = :courseId")
    Optional<Integer> findMaxDisplayOrderByCourseId(@Param("courseId") Long courseId);
}
