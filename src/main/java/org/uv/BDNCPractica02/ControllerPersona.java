/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package org.uv.BDNCPractica02;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Berna
 */
@RestController
@RequestMapping("/url")
public class ControllerPersona {
    @Autowired
    private RepositoryPersona repositoryPersona;
    
    @GetMapping("/persona/")
    public List<Persona> list() {
        return repositoryPersona.findAll();
    }
    
    @GetMapping("/persona/{id}")
    public Object get(@PathVariable String id) {
        Optional<Persona> resPersona = repositoryPersona.findById(id);
        if (resPersona.isPresent())
            return resPersona.get();
        else
            return null;
    }
    
    @PutMapping("/persona/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Persona personap) {
         Optional<Persona> optionalPersona = repositoryPersona.findById(id);
        if (optionalPersona.isPresent()) {
            Persona persona = optionalPersona.get();
            persona.setNombre(personap.getNombre());
            persona.setDireccion(personap.getDireccion());
            persona.setTelefono(personap.getTelefono());
            repositoryPersona.save(persona);
            return ResponseEntity.ok(persona);
        } else {
            return null;
        }
    }
    
    @PostMapping("/persona/")
    public ResponseEntity<?> post(@RequestBody Persona persona) {
        repositoryPersona.save(persona);
        return ResponseEntity.ok(persona);
    }
    
    @DeleteMapping("/persona/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Persona> elimPersona = repositoryPersona.findById(id);
        if (elimPersona.isPresent()) {
            repositoryPersona.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return null;
        }
    }
    
}
