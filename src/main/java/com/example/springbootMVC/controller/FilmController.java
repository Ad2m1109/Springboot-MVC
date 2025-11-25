package com.example.springbootMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springbootMVC.model.Film;
import com.example.springbootMVC.service.CategoryService;
import com.example.springbootMVC.service.FilmService;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller for handling film-related web requests.
 * Follows MVC pattern with service layer delegation.
 */
@Controller
@Slf4j
public class FilmController {

    private final FilmService filmService;
    private final CategoryService categoryService;

    public FilmController(FilmService filmService, CategoryService categoryService) {
        this.filmService = filmService;
        this.categoryService = categoryService;
    }

    /**
     * Display list of all films.
     */
    @GetMapping("/films")
    public String ListeFilms(Model model) {
        log.info("Displaying film list");
        model.addAttribute("film", filmService.getAllFilms());
        return "film";
    }

    /**
     * Delete a film by ID.
     */
    @GetMapping("/delete")
    public String deleteFilm(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        log.info("Request to delete film with id: {}", id);
        try {
            filmService.deleteFilm(id);
            redirectAttributes.addFlashAttribute("successMessage", "Film deleted successfully!");
        } catch (IllegalArgumentException e) {
            log.error("Error deleting film: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "redirect:/films";
    }

    /**
     * Show form to add a new film.
     */
    @GetMapping("/addfilm")
    public String addFilmForm(Model model) {
        log.info("Displaying add film form");
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("film", new Film());
        return "addfilm";
    }

    /**
     * Save a new film or update existing one.
     */
    @PostMapping("/add")
    public String saveFilm(@ModelAttribute Film film, RedirectAttributes redirectAttributes) {
        log.info("Request to save film: {}", film.getTitle());
        try {
            filmService.saveFilm(film);
            redirectAttributes.addFlashAttribute("successMessage", "Film added successfully!");
        } catch (IllegalArgumentException e) {
            log.error("Error saving film: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
            return "redirect:/addfilm";
        }
        return "redirect:/films";
    }
}
