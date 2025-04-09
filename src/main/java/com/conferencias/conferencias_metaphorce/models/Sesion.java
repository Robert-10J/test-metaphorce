package com.conferencias.conferencias_metaphorce.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "sesiones")
public class Sesion {

  @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;

  private LocalDate fecha;
  private LocalTime hora;

  @Column(name = "ponente_principal")
  private String ponentePrincipal;

  @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL)
  @JsonBackReference
  private Set<Registro> registros;

  public Sesion() {
  }  

  public Sesion(Long id, String titulo, LocalDate fecha, LocalTime hora, String ponentePrincipal) {
    this.id = id;
    this.titulo = titulo;
    this.fecha = fecha;
    this.hora = hora;
    this.ponentePrincipal = ponentePrincipal;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public LocalTime getHora() {
    return hora;
  }

  public void setHora(LocalTime hora) {
    this.hora = hora;
  }

  public String getPonentePrincipal() {
    return ponentePrincipal;
  }

  public void setPonentePrincipal(String ponentePrincipal) {
    this.ponentePrincipal = ponentePrincipal;
  }

  public Set<Registro> getRegistros() {
    return registros;
  }

  public void setRegistros(Set<Registro> registros) {
    this.registros = registros;
  }
}
