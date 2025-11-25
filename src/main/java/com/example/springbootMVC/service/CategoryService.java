package com.example.springbootMVC.service;

import java.util.List;
import java.util.Optional;

import com.example.springbootMVC.model.Category;

/**
 * Service interface for Category business logic.
 * Defines operations for managing categories.
 */
public interface CategoryService {

    /**
     * Retrieve all categories from the database.
     * 
     * @return List of all categories
     */
    List<Category> getAllCategories();

    /**
     * Find a category by its ID.
     * 
     * @param id The category ID
     * @return Optional containing the category if found
     */
    Optional<Category> getCategoryById(Long id);

    /**
     * Save a new category or update an existing one.
     * 
     * @param category The category to save
     * @return The saved category
     */
    Category saveCategory(Category category);

    /**
     * Delete a category by its ID.
     * 
     * @param id The category ID to delete
     * @throws IllegalArgumentException if category not found or has associated
     *                                  films
     */
    void deleteCategory(Long id);

    /**
     * Check if a category exists by ID.
     * 
     * @param id The category ID
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Check if a category has associated films.
     * 
     * @param id The category ID
     * @return true if category has films, false otherwise
     */
    boolean hasFilms(Long id);
}
