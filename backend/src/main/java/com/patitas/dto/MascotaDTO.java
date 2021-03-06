package com.patitas.dto;

import com.patitas.modelo.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MascotaDTO {
    private Long id;
    private Long organizacion_id;
    private Long duenio_id;
    private Especie especie;
    private String nombre;
    private String apodo;
    private Integer edad;
    private Sexo sexo;
    private String descripcion;
    private List<Foto> fotos;
    private List<Caracteristica> caracteristicas;
}
