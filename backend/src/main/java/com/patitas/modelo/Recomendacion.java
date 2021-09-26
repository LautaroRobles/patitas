package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Recomendacion {
    private MascotaEnAdopcion publicacion;
    private Date fecha;
}
