package com.expedienteclinico.expedienteclinico.repositories.almacen;

import com.expedienteclinico.expedienteclinico.models.almacen.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryModel, Long> {
    List<CategoryModel> findByNameContainingIgnoreCase(String name);
}