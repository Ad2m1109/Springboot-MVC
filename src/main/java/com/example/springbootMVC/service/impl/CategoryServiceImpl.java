package com.example.springbootMVC.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootMVC.model.Category;
import com.example.springbootMVC.repository.CategoryRepository;
import com.example.springbootMVC.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of CategoryService interface.
 * Handles business logic for category operations.
 */
@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        log.debug("Fetching all categories");
        List<Category> categories = categoryRepository.findAll();
        log.info("Retrieved {} categories", categories.size());
        return categories;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> getCategoryById(Long id) {
        log.debug("Fetching category with id: {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            log.info("Found category: {}", category.get().getNom());
        } else {
            log.warn("Category with id {} not found", id);
        }
        return category;
    }

    @Override
    public Category saveCategory(Category category) {
        log.debug("Saving category: {}", category.getNom());

        // Business validation
        if (category.getNom() == null || category.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        Category savedCategory = categoryRepository.save(category);
        log.info("Category saved successfully with id: {}", savedCategory.getId());
        return savedCategory;
    }

    @Override
    public void deleteCategory(Long id) {
        log.debug("Attempting to delete category with id: {}", id);

        Optional<Category> categoryOpt = categoryRepository.findById(id);

        if (categoryOpt.isEmpty()) {
            log.error("Cannot delete - Category with id {} not found", id);
            throw new IllegalArgumentException("Category with id " + id + " not found");
        }

        Category category = categoryOpt.get();

        // Business rule: Cannot delete category if it has associated films
        if (category.getFilms() != null && !category.getFilms().isEmpty()) {
            log.error("Cannot delete category {} - it has {} associated films",
                    category.getNom(), category.getFilms().size());
            throw new IllegalArgumentException(
                    "Cannot delete category '" + category.getNom() +
                            "' because it has " + category.getFilms().size() + " associated film(s)");
        }

        categoryRepository.deleteById(id);
        log.info("Category with id {} deleted successfully", id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        log.debug("Checking if category exists with id: {}", id);
        return categoryRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasFilms(Long id) {
        log.debug("Checking if category {} has films", id);
        Optional<Category> category = categoryRepository.findById(id);
        boolean hasFilms = category.isPresent() &&
                category.get().getFilms() != null &&
                !category.get().getFilms().isEmpty();
        log.debug("Category {} has films: {}", id, hasFilms);
        return hasFilms;
    }
}
