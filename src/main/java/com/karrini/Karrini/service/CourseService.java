package com.karrini.Karrini.service;

import com.karrini.Karrini.dto.CourseContentDto;
import com.karrini.Karrini.exception.CategoryNotFoundException;
import com.karrini.Karrini.exception.CourseNotFoundException;
import com.karrini.Karrini.exception.LectureNotFoundException;
import com.karrini.Karrini.exception.MaterialNotFoundException;
import com.karrini.Karrini.model.*;
import com.karrini.Karrini.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CategoryRepository categoryRepository;
    private final FileStorageService fileStorageService;
    private final InstructorRepository instructorRepository;

    public CourseService(CourseRepository courseRepository, LectureRepository lectureRepository, EnrollmentRepository enrollmentRepository, CategoryRepository categoryRepository, FileStorageService fileStorageService, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.lectureRepository = lectureRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.categoryRepository = categoryRepository;
        this.fileStorageService = fileStorageService;
        this.instructorRepository = instructorRepository;
    }

    public CourseContentDto getCourseContent(Long courseId, Integer displayOrder, UserDetails userDetails) {
        boolean isEnrolled = enrollmentRepository.existsByCourse_IdAndLearner_Email(courseId, userDetails.getUsername());
        if(isEnrolled){
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new CourseNotFoundException("Course with id: " + courseId + " not found"));

            Lecture lecture = lectureRepository.findByCourse_IdAndDisplayOrder(courseId, displayOrder)
                    .orElseThrow(() -> new LectureNotFoundException("Lecture with display order: " + displayOrder + " not found"));

            LearningMaterial material = lecture.getMaterial();
            if (material == null) {
                throw new MaterialNotFoundException("Material for lecture not found");
            }
            return new CourseContentDto(course, course.getLectures(), material, material.getMaterialType());
        } else {
            throw new AccessDeniedException("Learner is not enrolled in the course");
        }
    }

    @Transactional
    public boolean delete(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new CourseNotFoundException("Course not found for unenrollment.");
        }
        else{
            Course courseObj = course.get();
            courseObj.setDeleted(true);
            courseRepository.save(courseObj);
            return true;
        }
    }


    @Transactional
    public Course saveNewCourse(String name, String duration, String description, Long categoryId, Level level, Double price, MultipartFile imageFile, UserDetails userDetails) {
        Instructor instructor = instructorRepository.findByEmail(userDetails.getUsername());
        if (name == null || name.isBlank() || duration == null || duration.isBlank()) {
            throw new IllegalArgumentException("Name and duration are required.");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price must be non-negative.");
        }
        if (imageFile.isEmpty()) {
            throw new IllegalArgumentException("Please select a file to upload.");
        }

        Course course = new Course();
        course.setName(name);
        course.setDuration(duration);
        course.setDescription(description);
        course.setLevel(level);
        course.setPrice(price);
        course.setCourseStatus(CourseStatus.PENDING);
        course.setInstructor(instructor);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + categoryId + " is not found"));
        course.setCategory(category);

        String fileName = fileStorageService.storeFile(imageFile);
        course.setImageUrl(fileName);
        return courseRepository.save(course);
    }

    public Course saveEditedCourse(Long id, String name, String duration, String description, Long categoryId, Level level, Double price, MultipartFile imageFile, UserDetails userDetails) {
        Instructor instructor = instructorRepository.findByEmail(userDetails.getUsername());
        if (name == null || name.isBlank() || duration == null || duration.isBlank()) {
            throw new IllegalArgumentException("Name and duration are required.");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price must be non-negative.");
        }
        if (imageFile.isEmpty()) {
            throw new IllegalArgumentException("Please select a file to upload.");
        }
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            Course courseObj = course.get();
            courseObj.setName(name);
            courseObj.setDuration(duration);
            courseObj.setDescription(description);
            courseObj.setLevel(level);
            courseObj.setPrice(price);
            courseObj.setCourseStatus(CourseStatus.ACCEPTED);
            courseObj.setInstructor(instructor);
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CategoryNotFoundException("Category with id " + categoryId + " is not found"));
            courseObj.setCategory(category);

            String fileName = fileStorageService.storeFile(imageFile);
            courseObj.setImageUrl(fileName);
            return courseRepository.save(courseObj);
        }
        else {
            throw new CourseNotFoundException("Course with id" + id + " was not found.");
        }
    }
}
