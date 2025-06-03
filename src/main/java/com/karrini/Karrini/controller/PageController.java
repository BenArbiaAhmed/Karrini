package com.karrini.Karrini.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
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
