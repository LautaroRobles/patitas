package com.patitas.modelo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Publicacion {
    private Organizacion organizacion;
    private Boolean aprobada;
    private Categoria categoria;
    private Persona autor;
    private String titulo;
    private String cuerpo;
    private List<Foto> fotos;
}
