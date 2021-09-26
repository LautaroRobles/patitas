package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pregunta {
    private String texto;
    private Boolean activo;
    private Boolean obligatoria;
}
