package com.conferencias.conferencias_metaphorce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import com.conferencias.conferencias_metaphorce.dtos.RegistroDTO;
import com.conferencias.conferencias_metaphorce.models.Participante;
import com.conferencias.conferencias_metaphorce.models.Registro;
import com.conferencias.conferencias_metaphorce.models.Sesion;
import com.conferencias.conferencias_metaphorce.repositories.ParticipanteRepository;
import com.conferencias.conferencias_metaphorce.repositories.RegistroRepository;
import com.conferencias.conferencias_metaphorce.repositories.SesionRepository;

@Service
public class RegistroService {

    private final RegistroRepository registroRepository;

    public RegistroService(RegistroRepository registroRepository) {
        this.registroRepository = registroRepository;
    }

    public List<Registro> getAllRegistros() {
        return registroRepository.findAll();
    }

    public Optional<Registro> getRegistroById(Long id) {
        return registroRepository.findById(id);
    }

    // public Registro createRegistro(Registro registro) {
    //     if (registro.getParticipante() == null || registro.getSesion() == null) {
    //         throw new IllegalArgumentException("El participante y la sesión no pueden ser nulos.");
    //     }

    //     return registroRepository.save(registro);
    // }
    @Autowired
    private ParticipanteRepository participanteRepository;
    @Autowired
    private SesionRepository sesionRepository;

    public Registro createRegistro(RegistroDTO registroDTO) {
        Participante participante = participanteRepository.findById(registroDTO.getParticipante())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Sesion sesion = sesionRepository.findById(registroDTO.getSesion())
            .orElseThrow(() -> new RuntimeException("Sesión no encontrada"));

        Registro registro = registroDTO.ToEntity(sesion, participante);

        return registroRepository.save(registro);
    }

    public Registro updateRegistro(Long id, Registro registroActualizado) {
        Registro registroExistente = registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado con id: " + id));

        registroExistente.setSesion(registroActualizado.getSesion());
        registroExistente.setParticipante(registroActualizado.getParticipante());
        registroExistente.setFechaRegistro(registroActualizado.getFechaRegistro());

        return registroRepository.save(registroExistente);
    }

    public void deleteRegistro(Long id) {
        if (registroRepository.existsById(id)) {
            registroRepository.deleteById(id);
        } else {
            throw new RuntimeException("Registro no encontrado con id: " + id);
        }
    }

}
