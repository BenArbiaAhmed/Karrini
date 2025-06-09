package com.karrini.Karrini.repository;

import com.karrini.Karrini.model.VideoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoMaterialRepository extends JpaRepository<VideoMaterial, Long> {
    VideoMaterial getVideoMaterialById(Long id);
}
