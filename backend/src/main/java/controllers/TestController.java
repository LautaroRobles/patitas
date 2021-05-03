package controllers;

import model.Persona;
import model.validadorContrasenia.ValidacionContrasenia;
import model.validadorContrasenia.ValidacionLongitud;
import model.validadorContrasenia.ValidacionPeoresContrasenias;
import model.validadorContrasenia.ValidadorContrasenia;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Ejemplo de un REST Controller, se mapea un GET y un POST
@RestController
@RequestMapping("/api")
public class TestController {

    private static final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/validar-contrasenia")
    public Boolean validarContrasenia(@RequestParam String contrasenia)
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

        return validador.validarContrasenia(contrasenia);
    }
}
