package com.conferencias.conferencias_metaphorce.dtos;

import com.conferencias.conferencias_metaphorce.models.Participante;
import com.conferencias.conferencias_metaphorce.models.Registro;
import com.conferencias.conferencias_metaphorce.models.Sesion;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroDTO {

  //  private Long id;
    private Long participante;
    private Long sesion;

    public RegistroDTO(Registro registro) {
       // this.id = registro.getId();
        this.participante = registro.getParticipante().getId();
        this.sesion = registro.getSesion().getId();
    }

    public Registro ToEntity(Sesion sesion, Participante participante) {
        Registro registroEntity = new Registro();
       // registroEntity.setId(this.id);
        registroEntity.setParticipante(participante); // usar el parámetro
        registroEntity.setSesion(sesion);             // usar el parámetro
        return registroEntity;
    }    
}
