package com.conferencias.conferencias_metaphorce.controllers;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conferencias.conferencias_metaphorce.models.Sesion;
import com.conferencias.conferencias_metaphorce.services.SesionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;



@RestController
@RequestMapping(value = "/api/sesiones", produces = MediaType.APPLICATION_JSON_VALUE)
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @GetMapping()
    public List<Sesion> getAllSesions() {
        try {
            return sesionService.getAllSesiones();
        } catch (Exception e) {
            e.getMessage();
            return List.of(); // Return an empty list in case of an exception
        }
    }

    @GetMapping("/ordenar/{orden}")
    public List<Sesion> getAllSesionOrdered(@PathVariable String orden) {
        try {
            return sesionService.getAllSesionesOrdenadas(orden);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); 
        }
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getSesionById(@PathVariable Long id) {
        try {
            return sesionService.getSesionById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping(consumes = {"application/json", "application/json;charset=UTF-8"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createSesion(@RequestBody Sesion sesion) {
        try {
            Sesion createdSesion = sesionService.createSesion(sesion);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSesion);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable Long id, @RequestBody Sesion sesionActualizada) {
        try {
            Sesion sesionMod = sesionService.updateSesion(id, sesionActualizada);
            return ResponseEntity.ok(sesionMod);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSesion(@PathVariable Long id) {
        try {
            sesionService.deleteSesion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}