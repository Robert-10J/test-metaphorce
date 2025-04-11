package com.conferencias.conferencias_metaphorce.repositories;

import com.conferencias.conferencias_metaphorce.models.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    boolean existsByCorreo(String correo);
    boolean existsByCorreoAndPassword(String correo, String password);
}
