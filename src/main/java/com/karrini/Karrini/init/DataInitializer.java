//package com.karrini.Karrini.init;
//
//import com.karrini.Karrini.model.Instructor;
//import com.karrini.Karrini.repository.InstructorRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private final InstructorRepository instructorRepository;
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    public DataInitializer(InstructorRepository instructorRepository) {
//        this.instructorRepository = instructorRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Instructor instructor1 = new Instructor();
//        instructor1.setUsername("John");
//        instructor1.setEmail("john.doe@example.com");
//        instructor1.setPassword(passwordEncoder.encode("password123"));
//        instructorRepository.save(instructor1);
//
//        Instructor instructor2 = new Instructor();
//        instructor2.setUsername("Alice");
//        instructor2.setEmail("alice.smith@example.com");
//        instructor2.setPassword(passwordEncoder.encode("password123"));
//        instructorRepository.save(instructor2);
//
//        Instructor instructor3 = new Instructor();
//        instructor3.setUsername("Bob");
//        instructor3.setEmail("bob.johnson@example.com");
//        instructor3.setPassword(passwordEncoder.encode("password123"));
//        instructorRepository.save(instructor3);
//
//        Instructor instructor4 = new Instructor();
//        instructor4.setUsername("Clara");
//        instructor4.setEmail("clara.brown@example.com");
//        instructor4.setPassword(passwordEncoder.encode("password123"));
//        instructorRepository.save(instructor4);
//
//        Instructor instructor5 = new Instructor();
//        instructor5.setUsername("David");
//        instructor5.setEmail("david.wilson@example.com");
//        instructor5.setPassword(passwordEncoder.encode("password123"));
//        instructorRepository.save(instructor5);
//
//        Instructor instructor6 = new Instructor();
//        instructor6.setUsername("Eva");
//        instructor6.setEmail("eva.davis@example.com");
//        instructor6.setPassword(passwordEncoder.encode("password123"));
//        instructorRepository.save(instructor6);
//
//        Instructor instructor7 = new Instructor();
//        instructor7.setUsername("Frank");
//        instructor7.setEmail("frank.miller@example.com");
//        instructor7.setPassword(passwordEncoder.encode("password123"));
//        instructorRepository.save(instructor7);
//
//        Instructor instructor8 = new Instructor();
//        instructor8.setUsername("Grace");
//        instructor8.setEmail("grace.martinez@example.com");
//        instructor8.setPassword(passwordEncoder.encode("password123"));
//        instructorRepository.save(instructor8);
//
//        Instructor instructor9 = new Instructor();
//        instructor9.setUsername("Henry");
//        instructor9.setEmail("henry.garcia@example.com");
//        instructor9.setPassword(passwordEncoder.encode("password123"));
//        instructorRepository.save(instructor9);
//
//        Instructor instructor10 = new Instructor();
//        instructor10.setUsername("Isabel");
//        instructor10.setEmail("isabel.rodriguez@example.com");
//        instructor10.setPassword(passwordEncoder.encode("password123"));
//        instructorRepository.save(instructor10);
//    }
//}
