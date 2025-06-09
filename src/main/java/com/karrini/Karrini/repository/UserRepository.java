package com.karrini.Karrini.repository;

import java.util.List;
import java.util.Optional;

import com.karrini.Karrini.model.Role;
import com.karrini.Karrini.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(Role role);
    User findById(long id);
    Optional<User> findByEmail(String email);

}