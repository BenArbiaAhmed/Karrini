package com.karrini.Karrini.repository;

import java.util.List;

import com.karrini.Karrini.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);
    Category findById(long id);
    List<Category> findTop6ByOrderByIdAsc();
}