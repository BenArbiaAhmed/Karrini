package com.karrini.Karrini.controller;

import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.CourseStatus;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.service.PageDataService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class PageController {


    private final CourseRepository courseRepository;
    private final PageDataService pageDataService;

    public PageController(CourseRepository courseRepository, PageDataService pageDataService) {
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
    public String about(Model model) {
        model.addAttribute("instructors", pageDataService.getHomePageData().get("instructors"));
        return "about";
    }

    @GetMapping("/404")
    public String pageNotFound() {
        return "error/404";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(){
        return "forgot-password";
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
    public String course(@PathVariable Long id, Model model) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            model.addAttribute("course", course);
            model.addAttribute("starCount", course.getStarsCount());
            return "about-course";
        } else {
            return "error/404";
        }
    }

    @GetMapping("/mycourses")
    public String mycourses(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Map<String, List<Course>> data = pageDataService.getMyCoursesPageData(userDetails);
        if (data.containsKey("enrolledCourses")) {
            List<Course> enrolledCourses = data.get("enrolledCourses");
            model.addAttribute("enrolledCourses", enrolledCourses);
            return "my-courses-for-learner";
        } else if (data.containsKey("publishedCourses")) {
            List<Course> publishedCourses = data.get("publishedCourses");
            model.addAttribute("publishedCourses", publishedCourses);
            return "my-courses-for-instructor";
        }
        else {
            return "index";
        }
    }

    @GetMapping("/admin/dashbord")
    public String dashboard(Model model){
        List<Course> pendingCourses = courseRepository.findByIsDeletedFalseAndCourseStatus(CourseStatus.PENDING);
        model.addAttribute("pendingCourses", pendingCourses);
        return "admin-dashbord";
    }

}
