package com.conferencias.conferencias_metaphorce.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.conferencias.conferencias_metaphorce.models.Sesion;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SesionDTO {

    private Integer id;
    private String titulo;
    private LocalDate fecha;
    private LocalTime hora;
    private String ponentePrincipal;

    public SesionDTO(Sesion sesion) {
        this.id = sesion.getId().intValue();
        this.titulo = sesion.getTitulo();
        this.fecha = sesion.getFecha();
        this.hora = sesion.getHora();
        this.ponentePrincipal = sesion.getPonentePrincipal();
    }
}
