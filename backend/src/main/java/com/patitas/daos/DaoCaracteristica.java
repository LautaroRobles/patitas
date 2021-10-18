package com.patitas.daos;

import com.patitas.modelo.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoCaracteristica extends JpaRepository<Caracteristica, Long> {
}
