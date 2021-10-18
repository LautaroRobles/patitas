package com.patitas.daos;

import com.patitas.modelo.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoMascota extends JpaRepository<Mascota, Long> {}
