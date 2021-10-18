package com.patitas.daos;

import com.patitas.modelo.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoRecomendacion extends JpaRepository<Recomendacion, Long> {
}
