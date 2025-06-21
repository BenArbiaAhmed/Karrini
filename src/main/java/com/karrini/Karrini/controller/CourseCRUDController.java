package com.karrini.Karrini.controller;

import com.karrini.Karrini.exception.CourseNotFoundException;
import com.karrini.Karrini.model.Category;
import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.Level;
import com.karrini.Karrini.repository.CategoryRepository;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseCRUDController {

    private final CourseService courseService;
    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    public CourseCRUDController(CourseService courseService, CourseRepository courseRepository, CategoryRepository categoryRepository){
        this.courseService = courseService;
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
    }

    @DeleteMapping("/delete/{courseId}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Long courseId){
        boolean success = courseService.delete(courseId);
        System.out.println("Deletion called for courseId=" + courseId);
        if (success) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deletion failed");
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categories", categoryList);
        model.addAttribute("levels", Level.values());
        return "course-form";
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestParam("name") String name,
                                               @RequestParam("duration") String duration,
                                               @RequestParam("description") String description,
                                               @RequestParam("category_id") Long categoryId,
                                               @RequestParam("level") Level level,
                                               @RequestParam("price") Double price,
                                               @RequestParam("imageFile") MultipartFile imageFile,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        Course newCourse = courseService.saveNewCourse(name, duration, description, categoryId, level, price, imageFile, userDetails);
        return ResponseEntity.ok(newCourse);
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categories", categoryList);
        model.addAttribute("levels", Level.values());
        model.addAttribute("course", course);
        model.addAttribute("isEdit", true);
        return "course-form";
    }

    @PostMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
                                   @RequestParam("name") String name,
                                   @RequestParam("duration") String duration,
                                   @RequestParam("description") String description,
                                   @RequestParam("category_id") Long categoryId,
                                   @RequestParam("level") Level level,
                                   @RequestParam("price") Double price,
                                   @RequestParam("imageFile") MultipartFile imageFile,
                                   @PathVariable Long id,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        Course newCourse = courseService.saveEditedCourse(id, name, duration, description, categoryId, level, price, imageFile, userDetails);
        return ResponseEntity.ok(newCourse);
    }


}
