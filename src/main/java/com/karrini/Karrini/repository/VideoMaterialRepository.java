package com.karrini.Karrini.repository;

import com.karrini.Karrini.model.VideoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoMaterialRepository extends JpaRepository<VideoMaterial, Long> {
    VideoMaterial getVideoMaterialById(Long id);
}
