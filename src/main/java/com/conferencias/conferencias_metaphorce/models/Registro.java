package com.conferencias.conferencias_metaphorce.models;

import jakarta.persistence.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

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
  @JsonBackReference("participante-registro")
  private Participante participante;

  @ManyToOne
  @JoinColumn(name = "sesion_id", nullable = false)
  @JsonBackReference("sesion-registro")
  private Sesion sesion;

  @Column(name = "fecha_registro")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaRegistro = LocalDate.now();

  public Registro() {
  }

  public Registro(Long id, Participante participante, Sesion sesion, LocalDate fechaRegistro) {
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

  public LocalDate getFechaRegistro() {
    return fechaRegistro;
  }

  public void setFechaRegistro(LocalDate fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }
}
