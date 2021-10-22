package com.patitas.controladores;

import com.patitas.dto.RegistrarUsuarioDTO;
import com.patitas.excepciones.InvalidPasswordException;
import com.patitas.excepciones.UsernameAlreadyTakenException;
import com.patitas.seguridad.Rol;
import com.patitas.seguridad.Usuario;
import com.patitas.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class ControladorUsuario {
    @Autowired
    private ServicioUsuario servicioUsuario;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    Usuario registrarUsuario(@RequestBody RegistrarUsuarioDTO registrarUsuarioDTO) throws InvalidPasswordException, UsernameAlreadyTakenException {
        return servicioUsuario.registrar(
                registrarUsuarioDTO.getUsername(),
                registrarUsuarioDTO.getPassword(),
                registrarUsuarioDTO.getEmail(),
                Rol.ESTANDAR
            );
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    Usuario registrarAdmin(@RequestBody RegistrarUsuarioDTO registrarUsuarioDTO) throws InvalidPasswordException, UsernameAlreadyTakenException {
        return servicioUsuario.registrar(
                registrarUsuarioDTO.getUsername(),
                registrarUsuarioDTO.getPassword(),
                registrarUsuarioDTO.getEmail(),
                Rol.ADMINISTRADOR
            );
    }

    @PostMapping("/voluntario")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    Usuario registrarVoluntario(@RequestBody RegistrarUsuarioDTO registrarUsuarioDTO) throws InvalidPasswordException, UsernameAlreadyTakenException {
        return servicioUsuario.registrar(
                registrarUsuarioDTO.getUsername(),
                registrarUsuarioDTO.getPassword(),
                registrarUsuarioDTO.getEmail(),
                Rol.VOLUNTARIO
            );
    }
}
