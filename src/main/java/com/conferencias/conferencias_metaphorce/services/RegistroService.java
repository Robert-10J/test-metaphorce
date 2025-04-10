package com.conferencias.conferencias_metaphorce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.conferencias.conferencias_metaphorce.models.Registro;
import com.conferencias.conferencias_metaphorce.repositories.RegistroRepository;

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

    public Registro createRegistro(Registro registro) {
        if (registro.getParticipante() == null || registro.getSesion() == null) {
            throw new IllegalArgumentException("El participante y la sesión no pueden ser nulos.");
        }

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
