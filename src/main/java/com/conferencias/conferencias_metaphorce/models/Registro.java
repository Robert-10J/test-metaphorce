package com.conferencias.conferencias_metaphorce.models;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "registros", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"participante_id", "sesion_id"})
})
public class Registro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "participante_id", nullable = false)
  private Participante participante;

  @ManyToOne
  @JoinColumn(name = "sesion_id", nullable = false)
  private Sesion sesion;

  @Column(name = "fecha_registro")
  private LocalTime fechaRegistro = LocalTime.now();
}
