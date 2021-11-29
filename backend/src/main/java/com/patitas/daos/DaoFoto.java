package com.patitas.daos;

import com.patitas.modelo.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoFoto extends JpaRepository<Foto, Long> {
}
