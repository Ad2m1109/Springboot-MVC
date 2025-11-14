package com.example.springbootMVC.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Category
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String nom;
@OneToMany(mappedBy = "categorie")
@JsonIgnore
List<Film> films=new ArrayList<>();
public Category(String nom) {
this.nom = nom;
}
@Override
public String toString() {
return "Category{" + "id=" + id + ", nom='" + nom +
'\'' + '}';
}
}