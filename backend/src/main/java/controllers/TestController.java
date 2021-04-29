package controllers;

import model.Persona;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

// Ejemplo de un REST Controller, se mapea un GET y un POST
@RestController
@RequestMapping("/api")
public class TestController {

    private static final ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/persona")
    public Persona modificarPersona(@RequestBody Persona persona) {
        persona.setNombre(persona.getNombre() + "asd");
        return persona;
    }
    @GetMapping("/persona")
    public Persona damePersona() {
        Persona persona = new Persona();
        persona.setNombre("Tomi");
        persona.setContrasenia("1234");
        return persona;
    }
}
