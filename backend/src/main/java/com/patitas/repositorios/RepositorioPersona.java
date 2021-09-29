package com.patitas.repositorios;

import com.patitas.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioPersona extends JpaRepository<Persona, Long> {}
