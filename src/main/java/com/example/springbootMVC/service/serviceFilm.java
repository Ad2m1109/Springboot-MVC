package com.example.springbootMVC.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springbootMVC.model.Film;
import com.example.springbootMVC.repository.FilmRepository;

@Service
public class serviceFilm implements EServiceFilm
{
    FilmRepository filmRepository;    
    @Override
    public Film CreateFilm(Film f) {
        return filmRepository.save(f);
    }

    @Override
    public Film FindFilmById(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    @Override
    public List<Film> AllFilm() {
        return filmRepository.findAll();
    }

    @Override
    public Film UpdateFilm(Film f) {
        return filmRepository.save(f);
    }

    @Override
    public void DeleteFilm(Long id) {
        filmRepository.deleteById(id);
    }
    
}
