package com.example.apirestbiblioteca.Controller;

import com.example.apirestbiblioteca.Prestamo;
import com.example.apirestbiblioteca.Repository.PrestamoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoRepository prestamoRepository;

    public PrestamoController(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> getPrestamos() {
        List<Prestamo> p = this.prestamoRepository.findAll();
        return ResponseEntity.ok(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable int id) {
        Prestamo p = this.prestamoRepository.findById(id).get();
        return ResponseEntity.ok(p);
    }

    @PostMapping("/insert")
    public ResponseEntity<Prestamo> insertPrestamo(@Valid @RequestBody Prestamo p) {
        Prestamo p1 = this.prestamoRepository.save(p);
        return ResponseEntity.ok(p1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable int id, @RequestBody Prestamo p) {
        Prestamo p1 = this.prestamoRepository.findById(id).get();
        p1.setUsuario(p.getUsuario());
        p1.setEjemplar(p.getEjemplar());
        p1.setFechaInicio(p.getFechaInicio());
        p1.setFechaDevolucion(p.getFechaDevolucion());
        Prestamo prestamoActualizado = this.prestamoRepository.save(p);
        return ResponseEntity.ok(prestamoActualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePrestamo(@PathVariable int id) {
        this.prestamoRepository.deleteById(id);
        String m = "El prestamo con id " + id + " se ha eliminado.";
        return ResponseEntity.ok(m);
    }

}
