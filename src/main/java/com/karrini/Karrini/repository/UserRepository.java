package com.karrini.Karrini.repository;

import java.util.List;
import java.util.Optional;

import com.karrini.Karrini.model.Role;
import com.karrini.Karrini.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(Role role);
    User findById(long id);
    Optional<User> findByEmail(String email);
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = ?2 WHERE u.email = ?1")
    void updatePassword(String email, String password);
}