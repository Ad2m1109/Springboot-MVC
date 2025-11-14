package com.example.springbootMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootMVC.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
