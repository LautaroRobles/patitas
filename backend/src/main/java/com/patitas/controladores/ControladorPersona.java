package com.patitas.controladores;

import com.patitas.dto.RegistrarPersonaDTO;
import com.patitas.modelo.Persona;
import com.patitas.servicios.ServicioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persona")
public class ControladorPersona {

    @Autowired
    private ServicioPersona servicioPersona;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    Persona registrarPersona(@RequestBody RegistrarPersonaDTO registrarPersonaDTO) {
        return servicioPersona.registrarPersona(registrarPersonaDTO);
    }
}
