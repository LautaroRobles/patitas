package com.patitas.controladores;

import com.patitas.dto.ListadoMascotasDTO;
import com.patitas.dto.MascotaDTO;
import com.patitas.dto.NotificarMascotaDTO;
import com.patitas.modelo.buscadorHogares.HogarTransito;
import com.patitas.modelo.buscadorHogares.MascotaPerdida;
import com.patitas.modelo.enviadorNotificaciones.Mensaje;
import com.patitas.servicios.ServicioHogares;
import com.patitas.servicios.ServicioMascota;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/mascota")
public class ControladorMascota {

    @Autowired
    private ServicioMascota servicioMascota;
    @Autowired
    private ServicioHogares servicioHogares;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    MascotaDTO registrarMascota(@RequestBody MascotaDTO mascotaDTO) throws NotFoundException {
        return servicioMascota.registrarMascota(mascotaDTO);
    }

    @PostMapping("/varias")
    @ResponseStatus(HttpStatus.CREATED)
    ListadoMascotasDTO registrarVariasMascotas(@RequestBody ListadoMascotasDTO mascotasDTO) throws NotFoundException {
        return servicioMascota.registrarVariasMascotas(mascotasDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    MascotaDTO getMascota(@PathVariable Long id) throws NotFoundException {
        return servicioMascota.getMascota(id);
    }

    @PostMapping("/{id}/perdida")
    @ResponseStatus(HttpStatus.OK)
    Mensaje notificarMascotaPerdida(@PathVariable Long id, @RequestBody NotificarMascotaDTO notificarMascotaDTO) throws NotFoundException {
        return servicioMascota.encontreATuMascota(id, notificarMascotaDTO.getId_rescatista());
    }

    @GetMapping("/hogar")
    @ResponseStatus(HttpStatus.OK)
    List<HogarTransito> listadoHogaresAdecuados(@PathParam("pagina") int pagina, @RequestBody MascotaPerdida mascotaPerdida) {
        return servicioHogares.listadoHogaresAdecuados(mascotaPerdida, pagina);
    }
}
