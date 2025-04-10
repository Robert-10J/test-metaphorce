package com.conferencias.conferencias_metaphorce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.conferencias.conferencias_metaphorce.models.Sesion;
import com.conferencias.conferencias_metaphorce.repositories.SesionRepository;

@Service
public class SesionService {

    private final SesionRepository sesionRepository;

    public SesionService(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    public List<Sesion> getAllSesiones() {
        return sesionRepository.findAll();
    }

    public Sesion getSesionById(Long id) {
        return sesionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sesión no encontrada con id: " + id));
    }

    public Sesion createSesion(Sesion sesion) {
        if (sesion.getTitulo() == null || sesion.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título de la sesión no puede estar vacío.");
        }

        return sesionRepository.save(sesion);
    }

    public Sesion updateSesion(Long id, Sesion sesionActualizada) {
        Sesion sesionExistente = sesionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada con id: " + id));

        sesionExistente.setTitulo(sesionActualizada.getTitulo());
        sesionExistente.setFecha(sesionActualizada.getFecha());
        sesionExistente.setPonentePrincipal(sesionActualizada.getPonentePrincipal());

        return sesionRepository.save(sesionExistente);
    }

    public void deleteSesion(Long id) {
        if (sesionRepository.existsById(id)) {
            sesionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Sesion not found with id: " + id);
        }
    }
}
