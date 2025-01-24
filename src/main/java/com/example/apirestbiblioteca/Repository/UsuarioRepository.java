package com.example.apirestbiblioteca.Repository;

import com.example.apirestbiblioteca.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
