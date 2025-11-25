package com.example.springbootMVC;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.springbootMVC.model.Category;
import com.example.springbootMVC.model.Film;
import com.example.springbootMVC.service.CategoryService;
import com.example.springbootMVC.service.FilmService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringbootMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMvcApplication.class, args);
	}

	/**
	 * Initialize database with sample data on application startup.
	 * Uses service layer for proper business logic and validation.
	 */
	@Bean
	CommandLineRunner initData(FilmService filmService, CategoryService categoryService) {
		return args -> {
			log.info("Starting database initialization...");

			// Create categories
			Category c1 = new Category("Comedie");
			Category c2 = new Category("Horreur");
			Category c3 = new Category("Action");

			categoryService.saveCategory(c1);
			categoryService.saveCategory(c2);
			categoryService.saveCategory(c3);
			log.info("Created {} categories", 3);

			// Create films
			Film f1 = new Film("Harvard", "Film des années 60", 1962, c1);
			Film f2 = new Film("Malcolm", "Film des années 80", 1980, c2);
			Film f3 = new Film("Brusly", "Film des années 90", 1990, c3);

			filmService.saveFilm(f1);
			filmService.saveFilm(f2);
			filmService.saveFilm(f3);
			log.info("Created {} films", 3);

			// Display all films
			log.info("=== All Films ===");
			filmService.getAllFilms().forEach(f -> log.info("{}", f));

			log.info("Database initialization completed successfully!");
		};
	}
}
