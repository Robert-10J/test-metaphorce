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

  public Registro() {
  }

  public Registro(Long id, Participante participante, Sesion sesion, LocalTime fechaRegistro) {
    this.id = id;
    this.participante = participante;
    this.sesion = sesion;
    this.fechaRegistro = fechaRegistro;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Participante getParticipante() {
    return participante;
  }

  public void setParticipante(Participante participante) {
    this.participante = participante;
  }

  public Sesion getSesion() {
    return sesion;
  }

  public void setSesion(Sesion sesion) {
    this.sesion = sesion;
  }

  public LocalTime getFechaRegistro() {
    return fechaRegistro;
  }

  public void setFechaRegistro(LocalTime fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }
}
