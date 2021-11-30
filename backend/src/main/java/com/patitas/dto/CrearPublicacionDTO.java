package com.patitas.dto;

import com.patitas.modelo.Caracteristica;
import com.patitas.modelo.Categoria;
import com.patitas.modelo.Foto;
import com.patitas.modelo.Respuesta;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CrearPublicacionDTO {
    private Long organizacion_id;
    private Categoria categoria;
    private Long autor_id;
    private String titulo;
    private String cuerpo;
    private List<Foto> fotos;
    private List<Respuesta> respuestas;
    private List<Caracteristica> caracteristicas;
    private List<Caracteristica> preferencias;
}
