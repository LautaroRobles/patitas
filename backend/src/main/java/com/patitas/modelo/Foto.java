package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Tamanio tamanio;
    private String calidad;
    @Enumerated(EnumType.STRING)
    private FormatoImagen formato;
    @Lob
    private String imagenBase64;

    public void normalizar(Tamanio tamanioEstandar) {
        this.tamanio = tamanioEstandar;
    }
}
