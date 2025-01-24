package com.example.apirestbiblioteca.Controller;

import com.example.apirestbiblioteca.Ejemplar;
import com.example.apirestbiblioteca.Prestamo;
import com.example.apirestbiblioteca.Repository.EjemplarRepository;
import com.example.apirestbiblioteca.Libro;
import com.example.apirestbiblioteca.Repository.LibrosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejemplares")

public class EjemplarController {
    @Autowired
    EjemplarRepository ejemplarRepository;
    @Autowired
    LibrosRepository librosRepository;

    @Autowired
    public EjemplarController(EjemplarRepository ejemplarRepository, LibrosRepository librosRepository) {
        this.ejemplarRepository = ejemplarRepository;
        this.librosRepository = librosRepository;
    }

    //Select all
    @GetMapping
    public ResponseEntity<List<Ejemplar>> getAllEjemplar(){
        List<Ejemplar> ejemplar = ejemplarRepository.findAll();
        return ResponseEntity.ok(ejemplar);
    }

    //Select by id
    @GetMapping("{isbn}")
    @Cacheable
    public ResponseEntity<Ejemplar> getEjemplar(@PathVariable int isbn){
        Ejemplar e = this.ejemplarRepository.findById(isbn).get();
        return ResponseEntity.ok(e);
    }

    //Insert json
    @PostMapping("/insert")
    public ResponseEntity<Ejemplar> createEjemplar( @RequestBody Ejemplar ejemplar) {
        Ejemplar ejemplarGuardado = ejemplarRepository.save(ejemplar);
        return ResponseEntity.ok(ejemplarGuardado);
    }



    //PUT Update
    @PutMapping("/{id}")
    public ResponseEntity<Ejemplar> actulizarEjemplar( @PathVariable int id, @RequestBody Ejemplar ejemplarCambios){
        Ejemplar e = ejemplarRepository.findById(id).get();
        e.setIsbn(ejemplarCambios.getIsbn());
        e.setEstado(ejemplarCambios.getEstado());
        Ejemplar ejemplarPersist = this.ejemplarRepository.save(e);
        return ResponseEntity.ok(ejemplarPersist);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarEjemplar(@PathVariable int id){
        this.ejemplarRepository.deleteById(id);
        return ResponseEntity.ok("El ejemplar con id : "+ id + " se ha eliminado");
    }

}
