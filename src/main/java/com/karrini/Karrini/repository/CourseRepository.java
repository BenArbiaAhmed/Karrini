package com.karrini.Karrini.repository;

import java.util.List;
import java.util.Optional;

import com.karrini.Karrini.model.Category;
import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.CourseStatus;
import com.karrini.Karrini.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);
    Course findById(long id);
    List<Course> findTop6ByIsDeletedFalseOrderByIdAsc();
    List<Course> findCourseByCategory_Id(Long categoryId);
    List<Course> findTop9ByOrderByLearnerCountDesc();
    Long countByCategory(Category category);
    Long countByCategoryAndIsDeletedFalse(Category category);
    List<Course> findTop9ByIsDeletedFalseOrderByLearnerCountDesc();
    List<Course> findByIsDeletedFalseAndCourseStatus(CourseStatus courseStatus);
    Optional<Course> findCourseByIdAndInstructor_Email(Long id, String instructorEmail);
}