package com.example.springbootMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springbootMVC.model.Film;
import com.example.springbootMVC.repository.CategoryRepository;
import com.example.springbootMVC.repository.FilmRepository;

@Controller
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // List films
    @GetMapping("/films")
    public String ListeFilms(Model m) {
        m.addAttribute("film", filmRepository.findAll());
        return "film";
    }

    // Delete a film
    @GetMapping("/delete")
    public String deleteFilm(@RequestParam Long id) {
        filmRepository.deleteById(id);
        return "redirect:/films";
    }

    // Show add film form
    @GetMapping("/addfilm")
    public String addFilmForm(Model m){
        m.addAttribute("categories", categoryRepository.findAll());
        m.addAttribute("film", new Film());
        return "addfilm";
    }

    // Save film (IMPORTANT)
    @PostMapping("/add")
    public String saveFilm(@ModelAttribute Film film){
        filmRepository.save(film);
        return "redirect:/films";
    }
}
