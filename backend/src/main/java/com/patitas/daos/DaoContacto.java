package com.patitas.daos;

import com.patitas.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoContacto extends JpaRepository<Contacto, Long> {}
