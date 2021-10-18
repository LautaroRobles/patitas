package com.patitas.daos;

import com.patitas.modelo.TipoCaracteristica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoTipoCaracteristica extends JpaRepository<TipoCaracteristica, Long> {
}
