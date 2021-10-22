package com.patitas.controladores;

import com.patitas.dto.CrearOrganizacionDTO;
import com.patitas.modelo.Organizacion;
import com.patitas.modelo.Pregunta;
import com.patitas.modelo.TipoCaracteristica;
import com.patitas.servicios.ServicioOrganizacion;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizacion")
public class ControladorOrganizacion {

    @Autowired
    private ServicioOrganizacion servicioOrganizacion;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<Organizacion> listadoOrganizaciones() {
        return servicioOrganizacion.listadoOrganizaciones();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    Organizacion crearOrganizacion(@RequestBody CrearOrganizacionDTO crearOrganizacionDTO) {
        return servicioOrganizacion.crearOrganizacion(crearOrganizacionDTO);
    }

    @PostMapping("/{id}/caracteristica")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    TipoCaracteristica crearTipoCaracteristica(@PathVariable Long id, @RequestBody TipoCaracteristica tipoCaracteristica) throws NotFoundException {
        return servicioOrganizacion.crearTipoCaracteristica(id, tipoCaracteristica.getNombre(), tipoCaracteristica.getDisponible());
    }

    @PostMapping("/{idOrganizacion}/caracteristica/{idCaracteristica}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    TipoCaracteristica actualizarCaracteristica(@PathVariable Long idOrganizacion, @PathVariable Long idCaracteristica, @RequestBody TipoCaracteristica tipoCaracteristica) throws NotFoundException {
        return servicioOrganizacion.guardarTipoCaracteristica(idCaracteristica, tipoCaracteristica.getNombre(), tipoCaracteristica.getDisponible());
    }

    @PostMapping("/{idOrganizacion}/pregunta")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    Pregunta crearPregunta(@PathVariable Long idOrganizacion, @RequestBody Pregunta pregunta) throws NotFoundException {
        return servicioOrganizacion.crearPreguntaDisponible(idOrganizacion, pregunta.getTexto(), pregunta.getActivo(), pregunta.getObligatoria());
    }

    @PostMapping("/{idOrganizacion}/pregunta/{idPregunta}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    Pregunta actualizarPregunta(@PathVariable Long idOrganizacion, @PathVariable Long idPregunta, @RequestBody Pregunta pregunta) throws NotFoundException {
        return servicioOrganizacion.guardarPregunta(idPregunta, pregunta.getTexto(), pregunta.getActivo(), pregunta.getObligatoria());
    }
}
