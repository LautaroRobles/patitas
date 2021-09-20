package com.patitas.controladores;

import com.patitas.modelo.Mascota;
import com.patitas.repositorios.RepositorioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ControladorTest {

    @Autowired
    private RepositorioMascota repositorioMascota;

    @GetMapping("/mascota")
    public Mascota ObtenerMascota(@RequestParam long id) {
        Mascota mascota = (Mascota) repositorioMascota.findById(id).get();
        return mascota;
    }
    @PostMapping("/mascota")
    public ResponseEntity<Mascota> GuardarMascota(@RequestBody Mascota mascota) {
        Mascota mascotaGuardada = repositorioMascota.save(mascota);
        return new ResponseEntity<Mascota>(mascotaGuardada, HttpStatus.OK);
    }
}
