package com.karrini.Karrini.service;

import com.karrini.Karrini.model.Category;
import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.Instructor;
import com.karrini.Karrini.repository.CategoryRepository;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.repository.InstructorRepository;
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

    public PageDataService(CategoryRepository categoryRepository, CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
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

    // You would create similar methods for other pages, like getCoursesPageData()
}