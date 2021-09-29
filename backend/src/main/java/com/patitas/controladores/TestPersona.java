package com.patitas.controladores;

import com.patitas.modelo.Mascota;
import com.patitas.modelo.Persona;
import com.patitas.repositorios.RepositorioContacto;
import com.patitas.repositorios.RepositorioMascota;
import com.patitas.repositorios.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestPersona {
    @Autowired
    private RepositorioContacto repoContacto;
    @Autowired
    private RepositorioPersona repoPersona;
    @Autowired
    private RepositorioMascota repoMascota;

    @PostMapping("/persona")
    public Persona crearPersona(@RequestBody Persona persona) {
        return repoPersona.save(persona);
    }

    @PostMapping("/persona/{id}/mascota")
    public Persona agregarMascotaAPersona(@PathVariable Long id, @RequestBody List<Mascota> mascotas) {
        Persona duenio = repoPersona.findById(id).get();
        mascotas.forEach(mascota -> {
            mascota.setDuenio(duenio);
            repoMascota.save(mascota);
        });
        return repoPersona.getOne(id);
    }

    @GetMapping("/persona")
    public List<Persona> listadoPersonas() {
        return repoPersona.findAll();
    }

    @GetMapping("/persona/{id}")
    public Persona unaPersona(@PathVariable Long id) {
        Persona persona = repoPersona.findById(id).get();

        return persona;
    }
}
