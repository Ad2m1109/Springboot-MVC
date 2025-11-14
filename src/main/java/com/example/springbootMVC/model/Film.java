package com.example.springbootMVC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
public class Film {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String title;
private String description;
private int anneapp;
@ManyToOne
Category categorie;
public Film(String title, String description, int
anneapp) {
this.title = title;
this.description = description;
this.anneapp = anneapp;
}
public Film(String title, String description, int
anneapp, Category categorie) {
this.title = title;
this.description = description;
this.anneapp = anneapp;
this.categorie = categorie;
}
}