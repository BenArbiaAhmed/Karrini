package com.karrini.Karrini.controller;

import com.karrini.Karrini.model.Category;
import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.Instructor;
import com.karrini.Karrini.model.User;
import com.karrini.Karrini.repository.CategoryRepository;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.repository.InstructorRepository;
import com.karrini.Karrini.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        List<Category> categoryList = categoryRepository.findAll();
        categoryList = categoryList.subList(0, 6);
        model.addAttribute("categoris", categoryList);
        List<Course> courseList = courseRepository.findAll();
        courseList = courseList.subList(0, 6);
        model.addAttribute("courses", courseList);
        List<Instructor> instructorList = instructorRepository.findAll();
        instructorList = instructorList.subList(0, 4);
        model.addAttribute("instructors", instructorList);
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
    public String courses() {
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
}
