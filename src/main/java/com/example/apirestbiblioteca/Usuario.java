package com.example.apirestbiblioteca;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 15)
    @NotNull
    @Column(name = "dni", nullable = false, length = 15)
    private String dni;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    @Pattern(
            regexp = "^[A-Za-z0-9\\s]+$",
            message = "El nombre solo puede contener caracteres alfanuméricos y espacios"
    )
    private String nombre;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    @Pattern(
            regexp = "^[A-Za-z0-9]{1,50}@gmail.com$",
            message = "El email debe ser un correo de Gmail"
    )
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    @Pattern(
            regexp = "^[A-Za-z0-9]{4,12}$",
            message = "La contraseña debe ser alfanumérica y tener entre 4 y 12 caracteres"
    )
    private String password;

    @NotNull
    @Lob
    @Column(name = "tipo", nullable = false)
    @Pattern(
            regexp = "^(normal|administrador)$",
            message = "El tipo de usuario debe ser 'normal' o 'administrador'"
    )
    private String tipo;

    @Column(name = "penalizacionHasta")
    private LocalDate penalizacionHasta;

}