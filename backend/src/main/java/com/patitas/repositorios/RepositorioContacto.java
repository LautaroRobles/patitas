package com.patitas.repositorios;

import com.patitas.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioContacto extends JpaRepository<Contacto, Long> {}
