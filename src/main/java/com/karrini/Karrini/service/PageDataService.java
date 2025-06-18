package com.karrini.Karrini.service;

import com.karrini.Karrini.model.*;
import com.karrini.Karrini.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PageDataService {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final LearnerRepository learnerRepository;
    private final UserRepository userRepository;

    public PageDataService(CategoryRepository categoryRepository, CourseRepository courseRepository, InstructorRepository instructorRepository, EnrollmentRepository enrollmentRepository, LearnerRepository learnerRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.learnerRepository = learnerRepository;
        this.userRepository = userRepository;
    }


    public Map<String, Object> getHomePageData() {
        Map<String, Object> pageData = new HashMap<>();

        List<Category> categoryList = categoryRepository.findTop6ByOrderByIdAsc();
        List<Course> courseList = courseRepository.findTop6ByIsDeletedFalseOrderByIdAsc();
        List<Instructor> instructorList = instructorRepository.findTop4ByOrderByIdAsc();

        List<Long> courseCountForEachCategory = new ArrayList<>();
        for (Category category : categoryList) {
            courseCountForEachCategory.add(courseRepository.countByCategory(category));
        }

        pageData.put("categories", categoryList);
        pageData.put("courses", courseList);
        pageData.put("instructors", instructorList);
        pageData.put("courseCountForEachCategory", courseCountForEachCategory);

        return pageData;
    }

    public Map<String, Object> getCoursesPageData() {
        Map<String, Object> pageData = new HashMap<>();

        List<Category> categories = getTopCategories(6);
        List<Long> courseCounts = getActiveCourseCountsForCategories(categories);
        List<Course> popularCourses = getTopCoursesByLearnerCount(9);

        pageData.put("categories", categories);
        pageData.put("courses", popularCourses);
        pageData.put("courseCountForEachCategory", courseCounts);

        return pageData;
    }

    private List<Category> getTopCategories(int limit) {
        return categoryRepository.findTop6ByOrderByIdAsc();
    }

    private List<Long> getActiveCourseCountsForCategories(List<Category> categories) {
        return categories.stream()
                .map(c -> courseRepository.countByCategoryAndIsDeletedFalse(c))
                .collect(Collectors.toList());
    }

    private List<Course> getTopCoursesByLearnerCount(int limit) {
        return courseRepository.findTop9ByIsDeletedFalseOrderByLearnerCountDesc();
    }


    private boolean isCurrentUserInstructor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_INSTRUCTOR"));
    }

    private boolean isCurrentUserLearner() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_LEARNER"));
    }

    public Map<String, List<Course>> getMyCoursesPageData(UserDetails userDetails) {
        Map<String, List<Course>> pageData = new HashMap<>();
        if (isCurrentUserLearner()) {
            Learner learner = learnerRepository.findByEmail(userDetails.getUsername());
            List<Course> enrolledCourses = enrollmentRepository.findByLearner(learner).stream()
                .map(Enrollment::getCourse)
                .filter(course -> !course.isDeleted())
                .collect(Collectors.toList());
                pageData.put("enrolledCourses", enrolledCourses);
        }
        else if(isCurrentUserInstructor()){
            Instructor instructor = instructorRepository.findByEmail(userDetails.getUsername());
            List<Course> publishedCourses = instructor.getCourses();
            publishedCourses.removeIf(Course::isDeleted);
            pageData.put("publishedCourses", publishedCourses);
        }
        return pageData;
    }
}