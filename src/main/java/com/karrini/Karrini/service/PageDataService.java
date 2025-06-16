package com.karrini.Karrini.service;

import com.karrini.Karrini.model.*;
import com.karrini.Karrini.repository.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PageDataService {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final LearnerRepository learnerRepository;

    public PageDataService(CategoryRepository categoryRepository, CourseRepository courseRepository, InstructorRepository instructorRepository, EnrollmentRepository enrollmentRepository, LearnerRepository learnerRepository) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.learnerRepository = learnerRepository;
    }


    public Map<String, Object> getHomePageData() {
        Map<String, Object> pageData = new HashMap<>();

        List<Category> categoryList = categoryRepository.findTop6ByOrderByIdAsc();
        List<Course> courseList = courseRepository.findTop6ByOrderByIdAsc();
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
        List<Category> categoryList = categoryRepository.findTop6ByOrderByIdAsc();
        List<Long> courseCountForEachCategory = new ArrayList<Long>();
        for(Category category: categoryList){
            courseCountForEachCategory.add(courseRepository.countByCategory(category));
        }
        pageData.put("categories", categoryList);
        pageData.put("courses", courseRepository.findTop9ByOrderByLearnerCountDesc());
        pageData.put("courseCountForEachCategory", courseCountForEachCategory);
        return pageData;
    }

    public Map<String, List<Course>> getMyCoursesPageData(UserDetails userDetails) {
        Map<String, List<Course>> pageData = new HashMap<>();
        Learner learner = learnerRepository.findByEmail(userDetails.getUsername());
        List<Enrollment> enrollments = enrollmentRepository.findByLearner(learner);
        List<Course> enrolledCourses = new ArrayList<>();
        for(Enrollment enrollment: enrollments){
            enrolledCourses.add(enrollment.getCourse());
        }
        pageData.put("enrolledCourses", enrolledCourses);
        return pageData;
    }
}