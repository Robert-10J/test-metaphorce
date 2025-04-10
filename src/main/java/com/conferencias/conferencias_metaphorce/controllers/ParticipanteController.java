package com.conferencias.conferencias_metaphorce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conferencias.conferencias_metaphorce.models.Participante;
import com.conferencias.conferencias_metaphorce.repositories.ParticipanteRepository;
import com.conferencias.conferencias_metaphorce.services.ParticipanteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping( value = "/api/participantes", produces = {"application/json", "application/json;charset=UTF-8"})
public class ParticipanteController {
    
    @Autowired
    private ParticipanteService participanteService;

    @GetMapping
    public List<Participante> getAllParticipantes() {
        try {
            return participanteService.getAllParticipantes();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getParticipanteById(@PathVariable Long id) {
        try {
            return participanteService.getParticipanteById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            e.printStackTrace();
             Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(500).body("Error retrieving participant: " + e.getMessage());
        }
    }

    @PostMapping(consumes = {"application/json", "application/json;charset=UTF-8"})
    public ResponseEntity<?> createParticipante(@RequestBody Participante participante) {
        try {
            Participante createdParticipante = participanteService.createParticipante(participante);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdParticipante);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateParticipante(@PathVariable Long id, @RequestBody Participante participante) {
        try {
            Participante updatedParticipante = participanteService.updateParticipante(id, participante);
            return ResponseEntity.ok(updatedParticipante);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParticipante(@PathVariable Long id) {
        try {
            participanteService.deleteParticipante(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
