//package com.karrini.Karrini.service;
//
//
//import com.karrini.Karrini.exception.EmailExistsException;
//import com.karrini.Karrini.model.Instructor;
//import com.karrini.Karrini.model.Learner;
//import com.karrini.Karrini.model.Role;
//import com.karrini.Karrini.model.User;
//import com.karrini.Karrini.repository.InstructorRepository;
//import com.karrini.Karrini.repository.LearnerRepository;
//import com.karrini.Karrini.repository.UserRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SignUpService {
//
//    private final PasswordEncoder passwordEncoder;
//    private final LearnerRepository learnerRepository;
//    private final InstructorRepository instructorRepository;
//    private  final UserRepository userRepository;
//
//    public SignUpService(PasswordEncoder passwordEncoder, LearnerRepository learnerRepository, InstructorRepository instructorRepository, UserRepository userRepository) {
//        this.passwordEncoder = passwordEncoder;
//        this.learnerRepository = learnerRepository;
//        this.instructorRepository = instructorRepository;
//        this.userRepository = userRepository;
//    }
//
//    public ResponseEntity<Void> handleSignUp(User user) throws EmailExistsException {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        if(userRepository.findByEmail(user.getEmail()).isPresent()){
//            throw new EmailExistsException("Another Account uses this email adress: " + user.getEmail());
//        }
//        else if (user.getRole() == Role.LEARNER) {
//            Learner learner = new Learner();
//            learner.setFirstName(user.getFirstName());
//            learner.setLastName(user.getLastName());
//            learner.setUsername(user.getUsername());
//            learner.setEmail(user.getEmail());
//            learner.setPassword(user.getPassword());
//            learner.setRole(user.getRole());
//            learnerRepository.save(learner);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        } else if (user.getRole() == Role.INSTRUCTOR) {
//            Instructor instructor = new Instructor();
//            instructor.setFirstName(user.getFirstName());
//            instructor.setLastName(user.getLastName());
//            instructor.setUsername(user.getUsername());
//            instructor.setEmail(user.getEmail());
//            instructor.setPassword(user.getPassword());
//            instructor.setRole(user.getRole());
//            instructorRepository.save(instructor);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//}

package com.karrini.Karrini.service; // Or your correct package

import com.karrini.Karrini.exception.EmailExistsException;
import com.karrini.Karrini.model.*;
import com.karrini.Karrini.repository.InstructorRepository;
import com.karrini.Karrini.repository.LearnerRepository;
import com.karrini.Karrini.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final LearnerRepository learnerRepository;
    private final InstructorRepository instructorRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpService(UserRepository userRepository, LearnerRepository learnerRepository, InstructorRepository instructorRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.learnerRepository = learnerRepository;
        this.instructorRepository = instructorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseEntity<Void> handleSignUp(User userData) throws EmailExistsException {
        if (userRepository.findByEmail(userData.getEmail()).isPresent()) {
            throw new EmailExistsException("Another Account uses this email address: " + userData.getEmail());
        }

        if (userData.getRole() == Role.LEARNER) {
            Learner learner = new Learner();
            populateUserDetails(learner, userData);
            learnerRepository.save(learner);

        } else if (userData.getRole() == Role.INSTRUCTOR) {
            Instructor instructor = new Instructor();
            populateUserDetails(instructor, userData);
            instructorRepository.save(instructor);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * @param entity The final entity to be saved (e.g., a Learner or Instructor instance)
     * @param dto The User object created from the JSON request
     */
    private void populateUserDetails(User entity, User dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
}