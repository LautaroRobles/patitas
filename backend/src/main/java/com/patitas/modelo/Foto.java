package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Foto {
    private Tamanio tamanio;
    private String calidad;
    private FormatoImagen formato;

    public void normalizar(Tamanio tamanioEstandar) {
        this.tamanio = tamanioEstandar;
    }
}
