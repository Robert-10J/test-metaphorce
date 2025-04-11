package com.conferencias.conferencias_metaphorce.services;

import java.util.ArrayList;
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
        List<Participante> participantes = participanteRepository.findAll();
        return mergeSort(participantes);
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

    /**
     * Implementación del algoritmo Merge Sort para ordenar por primera letra del nombre
     */
    private List<Participante> mergeSort(List<Participante> lista) {
        // Caso base: si la lista tiene 0 o 1 elementos, ya está ordenada
        if (lista.size() <= 1) {
            return lista;
        }
        
        // Se encuentra el punto medio de la lista
        int puntoMedio = lista.size() / 2;
        
        // Dividimos la lista en dos mitades
        List<Participante> listaIzquierda = new ArrayList<>(lista.subList(0, puntoMedio));
        List<Participante> listaDerecha = new ArrayList<>(lista.subList(puntoMedio, lista.size()));
        
        // Ordenamos recursivamente ambas mitades
        listaIzquierda = mergeSort(listaIzquierda);
        listaDerecha = mergeSort(listaDerecha);
        
        // Combinamos las listas ordenadas
        return merge(listaIzquierda, listaDerecha);
    }

       
    /**
     * Método para combinar dos listas ordenadas en una sola lista ordenada
     */
    private List<Participante> merge(List<Participante> listaIzquierda, List<Participante> listaDerecha) {
        List<Participante> listaOrdenada = new ArrayList<>();
        int indiceIzquierda = 0;
        int indiceDerecha = 0;
        
        // Mientras tengamos elementos en ambas listas
        while (indiceIzquierda < listaIzquierda.size() && indiceDerecha < listaDerecha.size()) {
            // Obtenemos la primera letra de cada nombre
            char letraIzquierda = obtenerPrimeraLetra(listaIzquierda.get(indiceIzquierda).getNombre());
            char letraDerecha = obtenerPrimeraLetra(listaDerecha.get(indiceDerecha).getNombre());
            
            // Comparamos y agregamos el elemento menor a la lista ordenada
            if (letraIzquierda <= letraDerecha) {
                listaOrdenada.add(listaIzquierda.get(indiceIzquierda));
                indiceIzquierda++;
            } else {
                listaOrdenada.add(listaDerecha.get(indiceDerecha));
                indiceDerecha++;
            }
        }
        
        // Agregamos los elementos restantes de la lista izquierda (si hay)
        while (indiceIzquierda < listaIzquierda.size()) {
            listaOrdenada.add(listaIzquierda.get(indiceIzquierda));
            indiceIzquierda++;
        }
        
        // Agregamos los elementos restantes de la lista derecha (si hay)
        while (indiceDerecha < listaDerecha.size()) {
            listaOrdenada.add(listaDerecha.get(indiceDerecha));
            indiceDerecha++;
        }
        
        return listaOrdenada;
    }

    /**
     * Método auxiliar para obtener la primera letra del primer nombre de un participante.
     */
    private char obtenerPrimeraLetra(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return Character.MIN_VALUE; // Valor mínimo para nombres vacíos
        }
        
        // Obtenemos el primer nombre (en caso de tener más de una palabra)
        String primerNombre = nombre.split(" ")[0];
        
        // Retornamos la primera letra en minúscula para hacer la comparación insensible a mayúsculas/minúsculas
        return Character.toLowerCase(primerNombre.charAt(0));
    }
}
