package com.expedienteclinico.expedienteclinico.services.almacen;

import com.expedienteclinico.expedienteclinico.models.almacen.CategoryModel;
import com.expedienteclinico.expedienteclinico.repositories.almacen.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository iCategoryRepository;

    public List<CategoryModel> getAllCategories() {
        return iCategoryRepository.findAll();
    }

    public CategoryModel saveCategory(CategoryModel category) {
        return iCategoryRepository.save(category);
    }

    public Optional<CategoryModel> getCategoryById(Long id) {
        return iCategoryRepository.findById(id);
    }

    public void deleteCategory(Long id) {
        iCategoryRepository.deleteById(id);
    }

    public List<CategoryModel> findByNameContaining(String name) {
        return iCategoryRepository.findByNameContainingIgnoreCase(name);
    }
}
