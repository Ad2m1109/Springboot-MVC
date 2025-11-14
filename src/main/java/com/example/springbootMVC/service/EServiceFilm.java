package com.example.springbootMVC.service;

import java.util.List;

import com.example.springbootMVC.model.Film;

public interface EServiceFilm
{
public Film CreateFilm(Film f);
public Film FindFilmById(Long id);
public List<Film> AllFilm();
public Film UpdateFilm(Film f);
public void DeleteFilm(Long id);
}