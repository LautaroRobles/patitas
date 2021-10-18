package com.patitas.dto;

import com.patitas.modelo.PosicionGeografica;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrearOrganizacionDTO {
    private String nombre;
    private PosicionGeografica ubicacion;
    private String email;
}
