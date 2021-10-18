package com.patitas.dto;

import com.patitas.modelo.enviadorNotificaciones.TipoNotificacion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RegistrarContactoDTO {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private List<TipoNotificacion> tipoNotificaciones;
}
