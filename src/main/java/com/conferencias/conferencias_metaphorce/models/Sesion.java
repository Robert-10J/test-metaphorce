package com.conferencias.conferencias_metaphorce.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

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
  private Set<Registro> registros;

  
}
