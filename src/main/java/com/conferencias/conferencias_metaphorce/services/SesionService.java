package com.conferencias.conferencias_metaphorce.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public List<Sesion> getAllSesionesOrdenadas(String orden) {
        List<Sesion> sesiones = sesionRepository.findAll();
        
        // Verificamos si el orden es ascendente o descendente
        boolean ascendente = !orden.equalsIgnoreCase("desc");
        
        // Aplicamos quicksort para ordenar por fecha
        quicksortPorFecha(sesiones, 0, sesiones.size() - 1, ascendente);
        
        return sesiones;
    }

    private void quicksortPorFecha(List<Sesion> sesiones, int inicio, int fin, boolean ascendente) {
        if (inicio < fin) {
            int indiceParticion = particionarPorFecha(sesiones, inicio, fin, ascendente);
            
            // Ordenamos recursivamente las dos sub-listas
            quicksortPorFecha(sesiones, inicio, indiceParticion - 1, ascendente);
            quicksortPorFecha(sesiones, indiceParticion + 1, fin, ascendente);
        }
    }

    private int particionarPorFecha(List<Sesion> sesiones, int inicio, int fin, boolean ascendente) {
        // Usamos el último elemento como pivote
        LocalDate fechaPivote = obtenerFecha(sesiones.get(fin));
        
        int i = inicio - 1; // Índice del elemento más pequeño
        
        for (int j = inicio; j < fin; j++) {
            LocalDate fechaActual = obtenerFecha(sesiones.get(j));
            
            // Si ordenamos ascendentemente, queremos fechaActual <= fechaPivote
            // Si ordenamos descendentemente, queremos fechaActual >= fechaPivote
            boolean debeIntercambiar;
            if (ascendente) {
                debeIntercambiar = fechaActual.compareTo(fechaPivote) <= 0;
            } else {
                debeIntercambiar = fechaActual.compareTo(fechaPivote) >= 0;
            }
            
            if (debeIntercambiar) {
                i++;
                // Intercambiamos sesiones[i] y sesiones[j]
                Sesion temp = sesiones.get(i);
                sesiones.set(i, sesiones.get(j));
                sesiones.set(j, temp);
            }
        }
        
        // Intercambiamos sesiones[i+1] y sesiones[fin] (o el pivote)
        Sesion temp = sesiones.get(i + 1);
        sesiones.set(i + 1, sesiones.get(fin));
        sesiones.set(fin, temp);
        
        return i + 1;
    }

    private LocalDate obtenerFecha(Sesion sesion) {
        // Suponiendo que el campo fecha es un String en formato "yyyy-MM-dd"
        return LocalDate.parse(sesion.getFechaString());
    }


    public Optional<Sesion> getSesionById(Long id) {
        return sesionRepository.findById(id);
    }

    public Sesion createSesion(Sesion sesion) {
        if (sesion.getTitulo() == null || sesion.getTitulo().trim().isEmpty() || sesion.getFecha() == null) {
            throw new IllegalArgumentException("El título de la sesión no puede estar vacío.");
        }

        if (sesionRepository.existsByTitulo(sesion.getTitulo())) {
            throw new IllegalArgumentException("Ya existe una sesión con el título: " + sesion.getTitulo());
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
