package com.patitas.servicios;

import com.patitas.modelo.buscadorHogares.BuscadorHogarTransito;
import com.patitas.modelo.buscadorHogares.HogarTransito;
import com.patitas.modelo.buscadorHogares.MascotaPerdida;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioHogares {

    private BuscadorHogarTransito buscadorHogarTransito = BuscadorHogarTransito.getInstancia();

    public List<HogarTransito> listadoHogaresAdecuados(MascotaPerdida mascotaPerdida, int pagina) {
        return buscadorHogarTransito.hogaresAdecuados(mascotaPerdida, pagina);
    }
}
