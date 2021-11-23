package com.patitas.daos;

import com.patitas.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoCategoria extends JpaRepository<Categoria, Long> {
}
