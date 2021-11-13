package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Organizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @OneToOne(cascade = CascadeType.ALL)
    private PosicionGeografica ubicacion;
    private String email;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_organizacion")
    private List<TipoCaracteristica> caracteristicasDisponibles;
    @OneToMany(mappedBy = "organizacion",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Publicacion> publicaciones;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_organizacion")
    private List<Pregunta> preguntasAdopcion;
    @OneToOne(cascade = CascadeType.ALL)
    private Tamanio tamanioEstandar;
}


