package com.patitas.modelo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_organizacion")
    @JsonIgnore
    private Organizacion organizacion;
    private Boolean aprobada;
    @OneToOne(cascade = CascadeType.ALL)
    private Categoria categoria;
    @OneToOne(cascade = CascadeType.ALL)
    private Persona autor;
    private String titulo;
    private String cuerpo;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_publicacion")
    private List<Foto> fotos;
}
