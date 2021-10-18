package com.patitas.daos;

import com.patitas.modelo.MascotaEnAdopcion;
import com.patitas.modelo.Publicacion;
import com.patitas.modelo.QuieroAdoptar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DaoPublicacion extends JpaRepository<Publicacion, Long> {
    @Query("from QuieroAdoptar")
    public List<QuieroAdoptar> findAllQuieroAdoptar();
    @Query("from MascotaEnAdopcion ")
    public List<MascotaEnAdopcion> findAllMascotaEnAdopcion();
}
