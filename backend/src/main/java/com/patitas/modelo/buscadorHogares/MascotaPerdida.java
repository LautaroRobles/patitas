package com.patitas.modelo.buscadorHogares;

import com.patitas.modelo.Especie;
import com.patitas.modelo.PosicionGeografica;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MascotaPerdida {
    private Especie especie;
    private Tamanio tamanio;
    private PosicionGeografica posicionGeografica;
    private List<String> caracteristicas;
    private int radioMetros;
}
