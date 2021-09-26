package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@Table
public class Mascota{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private Organizacion organizacion;
    @Transient
    private Persona duenio;
    private Especie especie;
    private String nombre;
    private String apodo;
    private Integer edad;
    private Sexo sexo;
    private String descripcion;
    @Transient
    private List<Foto> fotos;
    @Transient
    private List<Caracteristica> caracteristicas;
}
