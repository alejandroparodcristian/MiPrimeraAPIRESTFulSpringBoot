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

    public boolean ValidarDNI(String dni) {
        String[] letras = dni.split("");
        String[] dni_letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        if (letras.length != 9){
            return false;
        }
        if (Character.isLetter(letras[0].charAt(0))){
            int numeros = Integer.parseInt(dni.substring(1, 8));
            if (letras[0].equals("X")) {
                if (letras[8].equals(dni_letras[numeros % 23])){
                    return true;
                } else
                    return false;
            } else if (letras[0].equals("Y")) {
                String numeros_actualizado = "1" + String.valueOf(numeros);
                if (letras[8].equals(dni_letras[Integer.parseInt(numeros_actualizado) % 23])){
                    return true;
                } else
                    return false;
            } else if (letras[0].equals("Z")) {
                String numeros_actualizado = "2" + String.valueOf(numeros);
                if (letras[8].equals(dni_letras[Integer.parseInt(numeros_actualizado) % 23])){
                    return true;
                } else
                    return false;
            } else
                return false;
        } else {
            int numeros = Integer.parseInt(dni.substring(0, 8));
            if (letras[8].equals(dni_letras[numeros % 23])) {
                return true;
            } else
                return false;
            }
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
    public ResponseEntity<Object> add(@Valid @RequestBody Usuario usuario) {
        if(ValidarDNI(usuario.getDni())){
            Usuario u = this.usuarioRepository.save(usuario);
            return ResponseEntity.ok(u);
        }else {
            return ResponseEntity.badRequest().body("El dni no es válido");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id ,@Valid @RequestBody Usuario usuario) {
        //buscamos el usuario en la bd
        if(ValidarDNI(usuario.getDni())){
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
        }else {
            return ResponseEntity.badRequest().body("El dni no es válido");
        }

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
