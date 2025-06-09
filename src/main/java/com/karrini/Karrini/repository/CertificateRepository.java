package com.karrini.Karrini.repository;

import com.karrini.Karrini.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Certificate findById(long id);
}
