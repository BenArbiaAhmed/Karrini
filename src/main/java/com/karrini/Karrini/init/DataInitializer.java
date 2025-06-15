//package com.karrini.Karrini.init;
//
//import com.karrini.Karrini.model.Instructor;
//import com.karrini.Karrini.model.Role;
//import com.karrini.Karrini.repository.InstructorRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private final InstructorRepository instructorRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public DataInitializer(InstructorRepository instructorRepository, PasswordEncoder passwordEncoder) {
//        this.instructorRepository = instructorRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args){
//        Instructor instructor1 = new Instructor();
//        instructor1.setFirstName("John");
//        instructor1.setLastName("Doe");
//        instructor1.setUsername("john.doe");
//        instructor1.setEmail("john.doe@example.com");
//        instructor1.setPassword(passwordEncoder.encode("password123"));
//        instructor1.setRole(Role.INSTRUCTOR);
//        instructorRepository.save(instructor1);
//
//        Instructor instructor2 = new Instructor();
//        instructor2.setFirstName("Alice");
//        instructor2.setLastName("Smith");
//        instructor2.setUsername("alice.smith");
//        instructor2.setEmail("alice.smith@example.com");
//        instructor2.setPassword(passwordEncoder.encode("password123"));
//        instructor2.setRole(Role.INSTRUCTOR);
//        instructorRepository.save(instructor2);
//
//        Instructor instructor3 = new Instructor();
//        instructor3.setFirstName("Bob");
//        instructor3.setLastName("Johnson");
//        instructor3.setUsername("bob.johnson");
//        instructor3.setEmail("bob.johnson@example.com");
//        instructor3.setPassword(passwordEncoder.encode("password123"));
//        instructor3.setRole(Role.INSTRUCTOR);
//        instructorRepository.save(instructor3);
//
//        Instructor instructor4 = new Instructor();
//        instructor4.setFirstName("Clara");
//        instructor4.setLastName("Brown");
//        instructor4.setUsername("clara.brown");
//        instructor4.setEmail("clara.brown@example.com");
//        instructor4.setPassword(passwordEncoder.encode("password123"));
//        instructor4.setRole(Role.INSTRUCTOR);
//        instructorRepository.save(instructor4);
//
//        Instructor instructor5 = new Instructor();
//        instructor5.setFirstName("David");
//        instructor5.setLastName("Wilson");
//        instructor5.setUsername("david.wilson");
//        instructor5.setEmail("david.wilson@example.com");
//        instructor5.setPassword(passwordEncoder.encode("password123"));
//        instructor5.setRole(Role.INSTRUCTOR);
//        instructorRepository.save(instructor5);
//
//        Instructor instructor6 = new Instructor();
//        instructor6.setFirstName("Eva");
//        instructor6.setLastName("Davis");
//        instructor6.setUsername("eva.davis");
//        instructor6.setEmail("eva.davis@example.com");
//        instructor6.setPassword(passwordEncoder.encode("password123"));
//        instructor6.setRole(Role.INSTRUCTOR);
//        instructorRepository.save(instructor6);
//
//        Instructor instructor7 = new Instructor();
//        instructor7.setFirstName("Frank");
//        instructor7.setLastName("Miller");
//        instructor7.setUsername("frank.miller");
//        instructor7.setEmail("frank.miller@example.com");
//        instructor7.setPassword(passwordEncoder.encode("password123"));
//        instructor7.setRole(Role.INSTRUCTOR);
//        instructorRepository.save(instructor7);
//
//        Instructor instructor8 = new Instructor();
//        instructor8.setFirstName("Grace");
//        instructor8.setLastName("Martinez");
//        instructor8.setUsername("grace.martinez");
//        instructor8.setEmail("grace.martinez@example.com");
//        instructor8.setPassword(passwordEncoder.encode("password123"));
//        instructor8.setRole(Role.INSTRUCTOR);
//        instructorRepository.save(instructor8);
//
//        Instructor instructor9 = new Instructor();
//        instructor9.setFirstName("Henry");
//        instructor9.setLastName("Garcia");
//        instructor9.setUsername("henry.garcia");
//        instructor9.setEmail("henry.garcia@example.com");
//        instructor9.setPassword(passwordEncoder.encode("password123"));
//        instructor9.setRole(Role.INSTRUCTOR);
//        instructorRepository.save(instructor9);
//
//        Instructor instructor10 = new Instructor();
//        instructor10.setFirstName("Isabel");
//        instructor10.setLastName("Rodriguez");
//        instructor10.setUsername("isabel.rodriguez");
//        instructor10.setEmail("isabel.rodriguez@example.com");
//        instructor10.setPassword(passwordEncoder.encode("password123"));
//        instructor10.setRole(Role.INSTRUCTOR);
//        instructorRepository.save(instructor10);
//    }
//}