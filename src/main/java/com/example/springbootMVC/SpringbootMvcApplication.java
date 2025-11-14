package com.example.springbootMVC;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.springbootMVC.model.Category;
import com.example.springbootMVC.model.Film;
import com.example.springbootMVC.repository.CategoryRepository;
import com.example.springbootMVC.repository.FilmRepository;

@SpringBootApplication
public class SpringbootMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMvcApplication.class, args);
	}
	//@Bean
	CommandLineRunner Start(FilmRepository filmRepository,
	CategoryRepository categoryRepository)
	{
	return args -> {
	Category c1=new Category("comedie");
	Category c2=new Category("Horreur");
	Category c3=new Category("Action");
	categoryRepository.save(c1);
	categoryRepository.save(c2);
	categoryRepository.save(c3);
	Film f1=new Film("harvard","film des anneés 60",1962,c1);
	Film f2=new Film("malcom","film des anneés 80",1980,c2);
	Film f3=new Film("Brusly","film des anneés 90",1980,c3);
	filmRepository.save(f1);
	filmRepository.save(f2);
	filmRepository.save(f2);
	filmRepository.save(f3);
	filmRepository.findAll().forEach(f->System.out.println(f));

};
} 
}
