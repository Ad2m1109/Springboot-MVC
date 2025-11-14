package com.example.springbootMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.springbootMVC.model.Film;
import com.example.springbootMVC.service.EServiceFilm;

@Controller
public class FilmControllerSrvice {
    @Autowired
    private EServiceFilm sf;
    public Film CreateFilm(Film f) {
        return sf.CreateFilm(f);
    }
    public Film FindFilmById(Long id) {
        return sf.FindFilmById(id);
    }
    public java.util.List<Film> AllFilm() {
        return sf.AllFilm();
    }
    public Film UpdateFilm(Film f) {
        return sf.UpdateFilm(f);
    }
    public void DeleteFilm(Long id) {
        sf.DeleteFilm(id);
    }

}
    
