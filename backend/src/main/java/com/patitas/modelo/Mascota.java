package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Mascota{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_organizacion")
    private Organizacion organizacion;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_duenio")
    private Persona duenio;
    @Enumerated(EnumType.STRING)
    private Especie especie;
    private String nombre;
    private String apodo;
    private Integer edad;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String descripcion;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mascota")
    private List<Foto> fotos;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mascota")
    private List<Caracteristica> caracteristicas;
}
