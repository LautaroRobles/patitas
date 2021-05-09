package com.patitas.controllers;

import com.patitas.model.validadorContrasenia.ValidacionContrasenia;
import com.patitas.model.validadorContrasenia.ValidacionLongitud;
import com.patitas.model.validadorContrasenia.ValidacionPeoresContrasenias;
import com.patitas.model.validadorContrasenia.ValidadorContrasenia;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.patitas.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

// Ejemplo de un REST Controller, se mapea un GET y un POST
@RestController
@RequestMapping("/api")
public class TestController {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/validar-contrasenia")
    public Boolean validarContrasenia(@RequestParam String password, @RequestParam String username, @RequestParam String email)
    {
        ValidadorContrasenia validador = new ValidadorContrasenia();

        // Creamos las validaciones y las guardamos en una lista de validaciones
        ValidacionLongitud validacionLongitud = new ValidacionLongitud();
        ValidacionPeoresContrasenias validacionPeoresContrasenias = new ValidacionPeoresContrasenias();
        List<ValidacionContrasenia> validaciones = new ArrayList<>();
        validaciones.add(validacionLongitud);
        validaciones.add(validacionPeoresContrasenias);

        // Establecemos al validador las validaciones que debe usar
        validador.setValidaciones(validaciones);

        return validador.validarContrasenia(password, username, email);
    }
}