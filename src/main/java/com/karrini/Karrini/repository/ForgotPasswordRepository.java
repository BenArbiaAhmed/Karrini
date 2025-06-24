package com.karrini.Karrini.repository;

import com.karrini.Karrini.model.ForgotPassword;
import com.karrini.Karrini.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {
    Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);
}
