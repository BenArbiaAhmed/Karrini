package com.karrini.Karrini.controller;


import com.karrini.Karrini.model.User;
import com.karrini.Karrini.service.SignUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService){
        this.signUpService = signUpService;
    }
    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity<Void> signup(@RequestBody User user) {
        return signUpService.handleSignUp(user);
    }
}
