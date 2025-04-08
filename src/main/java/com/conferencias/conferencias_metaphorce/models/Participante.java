package com.conferencias.conferencias_metaphorce.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "participantes")
public class Participante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String correo;
  private String institucion;

  @OneToMany(mappedBy = "participante", cascade = CascadeType.ALL)
  private Set<Registro> registros;
}
