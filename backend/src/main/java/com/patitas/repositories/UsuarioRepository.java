package com.patitas.repositories;

import com.patitas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// El repositorio es una interfaz donde establecemos que acciones CRUD estan disponibles
// En este ejemplo extendemos la interfaz JpaRepository pasando la clase a la que interactuamos en la base de datos
// y el TIPO de la PK (Long en este caso)

// En este caso uso JpaRepository porque a diferencia de CrusRepository, este expone un List<> en vez de un
// Iterable en findAll()
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
