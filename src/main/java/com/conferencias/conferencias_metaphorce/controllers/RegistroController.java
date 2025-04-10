package com.conferencias.conferencias_metaphorce.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.conferencias.conferencias_metaphorce.models.Registro;
import com.conferencias.conferencias_metaphorce.services.RegistroService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/registros")
public class RegistroController {

    @Autowired
    private RegistroService registroService;
    

    @GetMapping()
    public List<Registro> getAllRegistros() {
        try {
            return registroService.getAllRegistros();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Return an empty list in case of an exception
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRegistroById(@PathVariable Long id) {
        try {
            return registroService.getRegistroById(id)
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
