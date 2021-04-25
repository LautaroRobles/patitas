package controllers;

import model.Persona;
import model.dto.LeerPersonaDTO;
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
    public LeerPersonaDTO damePersona() {
        Persona persona = new Persona();
        persona.setNombre("Lautaro");
        persona.setContrasenia("1234");

        LeerPersonaDTO personaDTO = modelMapper.map(persona, LeerPersonaDTO.class);
        return personaDTO;
    }
}
