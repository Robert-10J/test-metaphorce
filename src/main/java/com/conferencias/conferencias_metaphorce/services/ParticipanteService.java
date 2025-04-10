package com.conferencias.conferencias_metaphorce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.conferencias.conferencias_metaphorce.models.Participante;
import com.conferencias.conferencias_metaphorce.repositories.ParticipanteRepository;

@Service
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public List<Participante> getAllParticipantes() {
        return participanteRepository.findAll();
    }

    public Optional<Participante> getParticipanteById(Long id) {
        return participanteRepository.findById(id);
    }

    public Participante createParticipante(Participante participante) {
        if (participante.getNombre() == null || participante.getNombre().trim().isEmpty() || participante.getCorreo() == null || participante.getInstitucion() == null) {
            throw new IllegalArgumentException("Datos del participante incompletos.");
        }

        if (participanteRepository.existsByCorreo(participante.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un participante con el correo: " + participante.getCorreo());
        }

        return participanteRepository.save(participante);
    }

    public Participante updateParticipante(Long id, Participante participanteActualizado) {
        Participante participanteExistente = participanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante no encontrado con id: " + id));

        participanteExistente.setNombre(participanteActualizado.getNombre());
        participanteExistente.setCorreo(participanteActualizado.getCorreo());
        participanteExistente.setInstitucion(participanteActualizado.getInstitucion());

        return participanteRepository.save(participanteExistente);
    }

    public void deleteParticipante(Long id) {
        if (participanteRepository.existsById(id)) {
            participanteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Participante no encontrado con id: " + id);
        }
    }
}
