package com.karrini.Karrini.repository;

import java.util.List;

import com.karrini.Karrini.model.Instructor;
import com.karrini.Karrini.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    List<Instructor> findByRole(Role role);
    Instructor findById(long id);
}