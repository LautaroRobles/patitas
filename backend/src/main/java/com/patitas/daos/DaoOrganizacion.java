package com.patitas.daos;

import com.patitas.modelo.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoOrganizacion extends JpaRepository<Organizacion, Long> {
}
