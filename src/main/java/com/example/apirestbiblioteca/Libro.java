package com.example.apirestbiblioteca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @Size(max = 20)
    @Column(name = "isbn", nullable = false, length = 20)
    @NotBlank(message = "No permite valores vacios")
    @NotNull(message = "No se perminten valores nulos")
    @Pattern(
            regexp = "^97[89]-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d$",
            message = "El ISBN debe seguir el formato ISBN-13 (Ej: 978-0-596-52068-7)"
    )
    private String isbn;

    @Size(max = 200)
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9\\s]+$",
            message = "El título solo puede contener caracteres alfanuméricos y espacios"
    )    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Size(max = 100)
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9\\s]+$",
            message = "El campo solo puede contener caracteres alfanuméricos y espacios"
    )    @Column(name = "autor", nullable = false, length = 100)
    private String autor;



}