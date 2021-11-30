package com.patitas.controladores;

import com.patitas.dto.CrearPublicacionDTO;
import com.patitas.excepciones.PublicationTypeException;
import com.patitas.modelo.Categoria;
import com.patitas.modelo.Publicacion;
import com.patitas.servicios.ServicioPublicacion;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicacion")
public class ControladorPublicacion {

    @Autowired
    private ServicioPublicacion servicioPublicacion;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<Publicacion> listadoPublicaciones() {
        return servicioPublicacion.listadoPublicaciones();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Publicacion getPublicacion(@PathVariable Long id) throws NotFoundException {
        return servicioPublicacion.findPublicacion(id);
    }

    @PostMapping("/{tipo}")
    @ResponseStatus(HttpStatus.CREATED)
    Publicacion crearPublicacion(@RequestBody CrearPublicacionDTO crearPublicacionDTO, @PathVariable String tipo) throws NotFoundException, PublicationTypeException {
        switch (tipo) {
            case "normal": return servicioPublicacion.crearPublicacion(crearPublicacionDTO);
            case "quieroadoptar": return servicioPublicacion.crearQuieroAdoptar(crearPublicacionDTO);
            case "mascotaenadopcion": return servicioPublicacion.crearMascotaEnAdopcion(crearPublicacionDTO);
            default: throw new PublicationTypeException("No existe una publicacion con tipo "+tipo);
        }
    }

    @PostMapping("/{id}/aprobar")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_VOLUNTARIO')")
    Publicacion aprobarPublicacion(@PathVariable Long id) throws NotFoundException {
        return servicioPublicacion.aprobarPublicacion(id);
    }

    @GetMapping("/categoria")
    @ResponseStatus(HttpStatus.OK)
    List<Categoria> listadoCategorias() {
        return servicioPublicacion.listadoCategorias();
    }

}
