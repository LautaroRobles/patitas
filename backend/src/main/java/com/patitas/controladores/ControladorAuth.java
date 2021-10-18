package com.patitas.controladores;

import com.patitas.dto.LoginUsuarioDTO;
import com.patitas.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ControladorAuth {

    @Autowired
    private ServicioUsuario servicioUsuario;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginUsuarioDTO loginUsuarioDTO) {
        return servicioUsuario.login(loginUsuarioDTO.getUsername(), loginUsuarioDTO.getPassword());
    }
}
