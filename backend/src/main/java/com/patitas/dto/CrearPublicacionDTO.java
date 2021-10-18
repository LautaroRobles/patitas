package com.patitas.dto;

import com.patitas.modelo.Caracteristica;
import com.patitas.modelo.Categoria;
import com.patitas.modelo.Respuesta;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CrearPublicacionDTO {
    private Long id_organizacion;
    private Categoria categoria;
    private Long id_autor;
    private String titulo;
    private String cuerpo;
    private List<Respuesta> respuestas;
    private List<Caracteristica> caracteristicas;
    private List<Caracteristica> preferencias;
}
