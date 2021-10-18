package com.patitas.daos;

import com.patitas.seguridad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DaoUsuario extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByUsername(String username);
}
