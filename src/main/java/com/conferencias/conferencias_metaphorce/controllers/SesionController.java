package com.conferencias.conferencias_metaphorce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conferencias.conferencias_metaphorce.models.Sesion;
import com.conferencias.conferencias_metaphorce.services.SesionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "/api/sesiones", produces = MediaType.APPLICATION_JSON_VALUE)
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @GetMapping()
    public List<Sesion> getMethodName() {
        return sesionService.getAllSesiones();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Sesion> getSesionById(@PathVariable Long id) {
        return sesionService.getSesionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = {"application/json", "application/json;charset=UTF-8"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Sesion> createSesion(@RequestBody Sesion sesion) {
        try {
            Sesion createdSesion = sesionService.createSesion(sesion);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSesion);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSesion(@PathVariable Long id) {
        sesionService.deleteSesion(id);
        return ResponseEntity.noContent().build();
    }
}