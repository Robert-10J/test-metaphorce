package com.conferencias.conferencias_metaphorce.dtos;

import com.conferencias.conferencias_metaphorce.models.Participante;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipanteDTO {

    private Long id;
    private String nombre;
    private String correo;
    private String institucion;

    public ParticipanteDTO(Participante participante) {
        this.id = participante.getId();
        this.nombre = participante.getNombre();
        this.correo = participante.getCorreo();
        this.institucion = participante.getInstitucion();
    }
}
