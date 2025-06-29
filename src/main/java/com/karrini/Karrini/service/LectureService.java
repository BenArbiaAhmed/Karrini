package com.karrini.Karrini.service;


import com.karrini.Karrini.dto.LectureDto;
import com.karrini.Karrini.exception.CourseNotFoundException;
import com.karrini.Karrini.model.*;
import com.karrini.Karrini.repository.CourseRepository;
import com.karrini.Karrini.repository.LectureRepository;
import com.karrini.Karrini.repository.TextMaterialRepository;
import com.karrini.Karrini.repository.VideoMaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
public class LectureService {

    private final  CourseRepository courseRepository;
    private final FileStorageService fileStorageService;
    private final LectureRepository lectureRepository;
    private final TextMaterialRepository textMaterialRepository;
    private final VideoMaterialRepository videoMaterialRepository;

    public LectureService(CourseRepository courseRepository, FileStorageService fileStorageService, LectureRepository lectureRepository, TextMaterialRepository textMaterialRepository, VideoMaterialRepository videoMaterialRepository){
        this.courseRepository = courseRepository;
        this.fileStorageService = fileStorageService;
        this.lectureRepository = lectureRepository;
        this.textMaterialRepository = textMaterialRepository;
        this.videoMaterialRepository = videoMaterialRepository;
    }


    @Transactional
    public ResponseEntity<String> saveNewLecture(Long courseId, LectureDto lectureDto, UserDetails userDetails){
        Course course = courseRepository.findCourseByIdAndInstructor_Email(courseId, userDetails.getUsername())
                .orElseThrow(() -> new AccessDeniedException("Access denied. You are not the owner of this course or the course does not exist."));
        Lecture newLecture = new Lecture();
        newLecture.setTitle(lectureDto.getTitle());
        newLecture.setDescription(lectureDto.getDescription());
        newLecture.setDuration(lectureDto.getDuration());
        newLecture.setCourse(course);


        Integer maxOrder = lectureRepository.findMaxDisplayOrderByCourseId(courseId).orElse(0);
        newLecture.setDisplayOrder(maxOrder + 1);
        lectureRepository.save(newLecture);

        if(Objects.equals(lectureDto.getLectureType(), "text")){
            TextMaterial textMaterial = new TextMaterial();
            textMaterial.setLecture(newLecture);
            textMaterial.setContent(lectureDto.getContent());
            textMaterial.setMaterialType(MaterialType.TEXT);
            textMaterialRepository.save(textMaterial);
        } else {
            VideoMaterial videoMaterial = new VideoMaterial();
            videoMaterial.setLecture(newLecture);
            String videoUrl = fileStorageService.storeFile(lectureDto.getVideoFile());
            videoMaterial.setVideoUrl(videoUrl);
            videoMaterial.setMaterialType(MaterialType.VIDEO);
            videoMaterialRepository.save(videoMaterial);
        }

        return ResponseEntity.ok("Lecture was added successfully");
    }
}
