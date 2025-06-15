package com.karrini.Karrini.controller;

import com.karrini.Karrini.exception.CourseNotFoundException;
import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.Lecture;
import com.karrini.Karrini.repository.CategoryRepository;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.service.PageDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class PageController {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private PageDataService pageDataService;

    public PageController(CategoryRepository categoryRepository, CourseRepository courseRepository, PageDataService pageDataService) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.pageDataService = pageDataService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("categories", pageDataService.getHomePageData().get("categoryList"));
        model.addAttribute("courses", pageDataService.getHomePageData().get("courses"));
        model.addAttribute("instructors", pageDataService.getHomePageData().get("instructors"));
        model.addAttribute("courseCountForEachCategory", pageDataService.getHomePageData().get("courseCountForEachCategory"));
        return "index";
    }


    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/404")
    public String pageNotFound() {
        return "error/404";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("categories", pageDataService.getCoursesPageData().get("categories"));
        model.addAttribute("courses", pageDataService.getCoursesPageData().get("courses"));
        model.addAttribute("courseCountForEachCategory", pageDataService.getCoursesPageData().get("courseCountForEachCategory"));
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
        return "courses-by-category";
    }
    @GetMapping("/course/{id}")
    public String course(@PathVariable Long id, Model model){
        if(courseRepository.findById(id).isPresent()) {
            Course course = courseRepository.findById(id).get();
            model.addAttribute("course", course);
            model.addAttribute("starCount", course.getStarsCount());
            return "course-details";
        }else{
            return "error/404";
        }
    }
}
