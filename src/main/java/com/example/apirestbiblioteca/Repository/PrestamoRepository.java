package com.example.apirestbiblioteca.Repository;

import com.example.apirestbiblioteca.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
}
