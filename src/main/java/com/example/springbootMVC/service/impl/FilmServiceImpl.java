package com.example.springbootMVC.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootMVC.model.Film;
import com.example.springbootMVC.repository.FilmRepository;
import com.example.springbootMVC.service.FilmService;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of FilmService interface.
 * Handles business logic for film operations.
 */
@Service
@Transactional
@Slf4j
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Film> getAllFilms() {
        log.debug("Fetching all films");
        List<Film> films = filmRepository.findAll();
        log.info("Retrieved {} films", films.size());
        return films;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Film> getFilmById(Long id) {
        log.debug("Fetching film with id: {}", id);
        Optional<Film> film = filmRepository.findById(id);
        if (film.isPresent()) {
            log.info("Found film: {}", film.get().getTitle());
        } else {
            log.warn("Film with id {} not found", id);
        }
        return film;
    }

    @Override
    public Film saveFilm(Film film) {
        log.debug("Saving film: {}", film.getTitle());

        // Business validation
        if (film.getTitle() == null || film.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Film title cannot be empty");
        }

        if (film.getAnneapp() < 1888) { // First film ever made
            throw new IllegalArgumentException("Release year must be 1888 or later");
        }

        Film savedFilm = filmRepository.save(film);
        log.info("Film saved successfully with id: {}", savedFilm.getId());
        return savedFilm;
    }

    @Override
    public void deleteFilm(Long id) {
        log.debug("Attempting to delete film with id: {}", id);

        if (!filmRepository.existsById(id)) {
            log.error("Cannot delete - Film with id {} not found", id);
            throw new IllegalArgumentException("Film with id " + id + " not found");
        }

        filmRepository.deleteById(id);
        log.info("Film with id {} deleted successfully", id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        log.debug("Checking if film exists with id: {}", id);
        return filmRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Film> getFilmsByCategory(Long categoryId) {
        log.debug("Fetching films for category id: {}", categoryId);
        List<Film> films = filmRepository.findAll().stream()
                .filter(film -> film.getCategorie() != null &&
                        film.getCategorie().getId().equals(categoryId))
                .collect(Collectors.toList());
        log.info("Found {} films for category id: {}", films.size(), categoryId);
        return films;
    }
}
