package com.example.springbootMVC.service;

import java.util.List;
import java.util.Optional;

import com.example.springbootMVC.model.Film;

/**
 * Service interface for Film business logic.
 * Defines operations for managing films.
 */
public interface FilmService {

    /**
     * Retrieve all films from the database.
     * 
     * @return List of all films
     */
    List<Film> getAllFilms();

    /**
     * Find a film by its ID.
     * 
     * @param id The film ID
     * @return Optional containing the film if found
     */
    Optional<Film> getFilmById(Long id);

    /**
     * Save a new film or update an existing one.
     * 
     * @param film The film to save
     * @return The saved film
     */
    Film saveFilm(Film film);

    /**
     * Delete a film by its ID.
     * 
     * @param id The film ID to delete
     * @throws IllegalArgumentException if film not found
     */
    void deleteFilm(Long id);

    /**
     * Check if a film exists by ID.
     * 
     * @param id The film ID
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Find films by category ID.
     * 
     * @param categoryId The category ID
     * @return List of films in the category
     */
    List<Film> getFilmsByCategory(Long categoryId);
}
