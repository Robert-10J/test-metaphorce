package com.conferencias.conferencias_metaphorce.dtos;

import com.conferencias.conferencias_metaphorce.models.Participante;
import com.conferencias.conferencias_metaphorce.models.Registro;
import com.conferencias.conferencias_metaphorce.models.Sesion;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroDTO {

    private Long id;
    private Participante participante;
    private Sesion sesion;

    public RegistroDTO(Registro registro) {
        this.id = registro.getId();
        this.participante = registro.getParticipante();
        this.sesion = registro.getSesion();
    }
}
