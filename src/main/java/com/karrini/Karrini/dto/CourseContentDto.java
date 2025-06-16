package com.karrini.Karrini.dto;

import com.karrini.Karrini.model.Course;
import com.karrini.Karrini.model.LearningMaterial;
import com.karrini.Karrini.model.Lecture;
import com.karrini.Karrini.model.MaterialType;

import java.util.List;

public class CourseContentDto {

    private Course course;
    private List<Lecture> lectures;
    private LearningMaterial material;
    private MaterialType materialType;

    public CourseContentDto(Course course, List<Lecture> lectures, LearningMaterial material, MaterialType materialType) {
        this.course = course;
        this.lectures = lectures;
        this.material = material;
        this.materialType = materialType;
    }

    public Course getCourse() {
        return course;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public LearningMaterial getMaterial() {
        return material;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }
}
