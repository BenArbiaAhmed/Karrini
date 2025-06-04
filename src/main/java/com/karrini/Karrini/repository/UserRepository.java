package com.karrini.Karrini.repository;

import java.util.List;

import com.karrini.Karrini.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(String role);
    User findById(long id);
}