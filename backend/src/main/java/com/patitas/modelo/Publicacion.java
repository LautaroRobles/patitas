package com.patitas.modelo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table
@DiscriminatorColumn(name="tipo")
@DiscriminatorValue(value="Normal")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_organizacion")
    private Organizacion organizacion;
    private Boolean aprobada;
    @OneToOne
    private Categoria categoria;
    @OneToOne
    private Persona autor;
    private String titulo;
    private String cuerpo;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_publicacion")
    private List<Foto> fotos;
}
