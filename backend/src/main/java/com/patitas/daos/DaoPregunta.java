package com.patitas.daos;

import com.patitas.modelo.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoPregunta extends JpaRepository<Pregunta, Long> {
}
