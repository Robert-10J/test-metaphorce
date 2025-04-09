package com.conferencias.conferencias_metaphorce.models;

import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
  @JsonManagedReference
  private Set<Registro> registros;

  public Participante() {
  }
  
  public Participante(Long id, String nombre, String correo, String institucion) {
    this.id = id;
    this.nombre = nombre;
    this.correo = correo;
    this.institucion = institucion;
  }

  // @Override
  // public String toString() {
  //   return "Participante [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", institucion=" + institucion
  //       + ", registros=" + registros + "]";
  // }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getInstitucion() {
    return institucion;
  }

  public void setInstitucion(String institucion) {
    this.institucion = institucion;
  }

  public Set<Registro> getRegistros() {
    return registros;
  }

  public void setRegistros(Set<Registro> registros) {
    this.registros = registros;
  }
}
