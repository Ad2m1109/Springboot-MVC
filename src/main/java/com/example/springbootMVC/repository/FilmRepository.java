package com.example.springbootMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootMVC.model.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {

}
