package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Organizacion {
    private String nombre;
    private PosicionGeografica ubicacion;
    private String email;
    private List<TipoCaracteristica> caracteristicasDisponibles;
    private List<Publicacion>publicaciones;
    private Tamanio tamanioEstandar;
}


