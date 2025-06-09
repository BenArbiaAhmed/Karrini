package com.karrini.Karrini.controller;

import com.karrini.Karrini.repository.CategoryRepository;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.repository.InstructorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("categories", categoryRepository.findTop6ByOrderByIdAsc());
        model.addAttribute("courses", courseRepository.findTop6ByOrderByIdAsc());
        model.addAttribute("instructors", instructorRepository.findTop4ByOrderByIdAsc());
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

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
}
