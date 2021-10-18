package com.patitas.daos;

import com.patitas.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoPersona extends JpaRepository<Persona, Long> {}
