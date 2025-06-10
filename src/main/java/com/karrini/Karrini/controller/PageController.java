package com.karrini.Karrini.controller;

import com.karrini.Karrini.model.Category;
import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.repository.CategoryRepository;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.repository.InstructorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public PageController(CategoryRepository categoryRepository, CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Category> categoryList = categoryRepository.findTop6ByOrderByIdAsc();
        model.addAttribute("categories", categoryList);
        model.addAttribute("courses", courseRepository.findTop6ByOrderByIdAsc());
        model.addAttribute("instructors", instructorRepository.findTop4ByOrderByIdAsc());
        List<Long> courseCountForEachCategory = new ArrayList<Long>();
        for(Category category: categoryList){
            courseCountForEachCategory.add(courseRepository.countByCategory(category));
        }
        model.addAttribute("courseCountForEachCategory", courseCountForEachCategory);
        return "index";
    }


    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/404")
    public String pageNotFound() {
        return "404";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        List<Category> categoryList = categoryRepository.findTop6ByOrderByIdAsc();
        List<Long> courseCountForEachCategory = new ArrayList<Long>();
        for(Category category: categoryList){
            courseCountForEachCategory.add(courseRepository.countByCategory(category));
        }
        model.addAttribute("categories", categoryList);
        model.addAttribute("courses", courseRepository.findTop9ByOrderByLearnerCountDesc());
        model.addAttribute("courseCountForEachCategory", courseCountForEachCategory);
        return "courses";
    }

    @GetMapping("/team")
    public String team() {
        return "team";
    }

    @GetMapping("/testimonial")
    public String testimonial() {
        return "testimonial";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/category/{id}")
    public String categoryByIdCourses(@PathVariable Long id, Model model){
        model.addAttribute("courses", courseRepository.findCourseByCategory_Id(id));
        return "categoryCourses";
    }
    @GetMapping("/course/{id}")
    public String course(@PathVariable Long id, Model model){
        if(courseRepository.findById(id).isPresent()) {
            Course course = courseRepository.findById(id).get();
            model.addAttribute("course", course);
            model.addAttribute("starCount", course.getStarsCount());
            return "courseDetails";
        }else{
            return "error/404";
        }
    }
}
