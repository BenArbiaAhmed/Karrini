package com.karrini.Karrini.repository;

import com.karrini.Karrini.model.TextMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextMaterialRepository extends JpaRepository<TextMaterial, Long> {
    TextMaterial findTextMaterialById(Long id);
}
