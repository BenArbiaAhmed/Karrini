package com.karrini.Karrini.repository;

import java.util.List;

import com.karrini.Karrini.model.Instructor;
import com.karrini.Karrini.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    List<Instructor> findTop4ByOrderByIdAsc();
    List<Instructor> findByRole(Role role);
    Instructor findById(long id);
    Instructor findByEmail(String username);
}