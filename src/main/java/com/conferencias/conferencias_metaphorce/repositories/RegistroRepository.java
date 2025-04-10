package com.conferencias.conferencias_metaphorce.repositories;

import com.conferencias.conferencias_metaphorce.models.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
}
