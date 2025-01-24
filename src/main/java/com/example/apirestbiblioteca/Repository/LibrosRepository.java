package com.example.apirestbiblioteca.Repository;

import com.example.apirestbiblioteca.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrosRepository extends JpaRepository<Libro, String> {
}
