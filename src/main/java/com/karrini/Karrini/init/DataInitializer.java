package com.karrini.Karrini.init;

import com.karrini.Karrini.model.User;
import com.karrini.Karrini.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword(passwordEncoder.encode("password123"));
        user1.setRole("ROLE_INSTRUCTOR");
        userRepository.save(user1);

        User user2 = new User();
        user2.setFirstName("Alice");
        user2.setLastName("Smith");
        user2.setEmail("alice.smith@example.com");
        user2.setPassword(passwordEncoder.encode("password123"));
        user2.setRole("ROLE_INSTRUCTOR");
        userRepository.save(user2);

        User user3 = new User();
        user3.setFirstName("Bob");
        user3.setLastName("Johnson");
        user3.setEmail("bob.johnson@example.com");
        user3.setPassword(passwordEncoder.encode("password123"));
        user3.setRole("ROLE_INSTRUCTOR");
        userRepository.save(user3);

        User user4 = new User();
        user4.setFirstName("Clara");
        user4.setLastName("Brown");
        user4.setEmail("clara.brown@example.com");
        user4.setPassword(passwordEncoder.encode("password123"));
        user4.setRole("ROLE_INSTRUCTOR");
        userRepository.save(user4);

        User user5 = new User();
        user5.setFirstName("David");
        user5.setLastName("Wilson");
        user5.setEmail("david.wilson@example.com");
        user5.setPassword(passwordEncoder.encode("password123"));
        user5.setRole("ROLE_INSTRUCTOR");
        userRepository.save(user5);

        User user6 = new User();
        user6.setFirstName("Eva");
        user6.setLastName("Davis");
        user6.setEmail("eva.davis@example.com");
        user6.setPassword(passwordEncoder.encode("password123"));
        user6.setRole("ROLE_INSTRUCTOR");
        userRepository.save(user6);

        User user7 = new User();
        user7.setFirstName("Frank");
        user7.setLastName("Miller");
        user7.setEmail("frank.miller@example.com");
        user7.setPassword(passwordEncoder.encode("password123"));
        user7.setRole("ROLE_INSTRUCTOR");
        userRepository.save(user7);

        User user8 = new User();
        user8.setFirstName("Grace");
        user8.setLastName("Martinez");
        user8.setEmail("grace.martinez@example.com");
        user8.setPassword(passwordEncoder.encode("password123"));
        user8.setRole("ROLE_INSTRUCTOR");
        userRepository.save(user8);

        User user9 = new User();
        user9.setFirstName("Henry");
        user9.setLastName("Garcia");
        user9.setEmail("henry.garcia@example.com");
        user9.setPassword(passwordEncoder.encode("password123"));
        user9.setRole("ROLE_INSTRUCTOR");
        userRepository.save(user9);

        User user10 = new User();
        user10.setFirstName("Isabel");
        user10.setLastName("Rodriguez");
        user10.setEmail("isabel.rodriguez@example.com");
        user10.setPassword(passwordEncoder.encode("password123"));
        user10.setRole("ROLE_INSTRUCTOR");
        userRepository.save(user10);

    }
}
