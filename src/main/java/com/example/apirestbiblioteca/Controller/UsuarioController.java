package com.example.apirestbiblioteca.Controller;

import com.example.apirestbiblioteca.Repository.UsuarioRepository;
import com.example.apirestbiblioteca.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

//RequestBody json
//pathVariable
public class UsuarioController {
    UsuarioRepository usuarioRepository;
    public UsuarioController() {
    }
    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }
    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getByIsbn(@PathVariable int id) {
        Usuario u = this.usuarioRepository.findById(id).get();
        return ResponseEntity.ok(u);
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> add(@Valid @RequestBody Usuario usuario) {
        Usuario u = this.usuarioRepository.save(usuario);
        return ResponseEntity.ok(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable int id ,@Valid @RequestBody Usuario usuario) {
        //buscamos el usuario en la bd
        Usuario usuarioBD = this.usuarioRepository.findById(id).get();
        //Lo guardamos y le hacemos los cambios nuevos
        Usuario usuarioActualizado = usuarioBD;
        usuarioActualizado.setNombre(usuario.getNombre());
        usuarioActualizado.setTipo(usuario.getTipo());
        usuarioActualizado.setEmail(usuario.getEmail());
        usuarioActualizado.setPassword(usuario.getPassword());
        usuarioActualizado.setPenalizacionHasta(usuario.getPenalizacionHasta());
        //Guardamos los cambios en la bd y lo enseñamos
        Usuario persist = this.usuarioRepository.save(usuarioActualizado);
        return ResponseEntity.ok().body(persist);
    }

    @DeleteMapping("/borrar/")
    public ResponseEntity<Usuario> delete(@RequestBody Usuario usuario) {
        this.usuarioRepository.delete(usuario);
        String m = "EL usuario con id " + usuario.getId() + " se ha borrado";
        return ResponseEntity.ok().body(usuario);
    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteId(@PathVariable int id) {
        Usuario U = this.usuarioRepository.findById(id).get();
        this.usuarioRepository.deleteById(id);
        String m = "EL usuario con id " + U.getId() + " se ha borrado";
        return ResponseEntity.ok().body(m);
    }
}
