package com.conferencias.conferencias_metaphorce.repositories;

import com.conferencias.conferencias_metaphorce.models.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
