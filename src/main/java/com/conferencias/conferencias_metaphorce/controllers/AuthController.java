package com.conferencias.conferencias_metaphorce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conferencias.conferencias_metaphorce.models.Participante;
import com.conferencias.conferencias_metaphorce.repositories.ParticipanteRepository;
import com.conferencias.conferencias_metaphorce.services.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final ParticipanteRepository participanteRepository;
    private final JwtService jwtService;

    public AuthController(ParticipanteRepository participanteRepository, JwtService jwtService) {
        this.participanteRepository = participanteRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Participante request) {
        if (participanteRepository.existsByCorreoAndPassword(request.getCorreo(), request.getPassword())) {
            String token = jwtService.generateToken(request.getCorreo());
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }
    

}
