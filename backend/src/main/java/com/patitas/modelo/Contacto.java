package com.patitas.modelo;

import com.patitas.modelo.enviadorNotificaciones.EnviadorNotificaciones;
import com.patitas.modelo.enviadorNotificaciones.Mensaje;
import com.patitas.modelo.enviadorNotificaciones.Notificacion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private List<Notificacion> formasNotificacion;

    public void enviarNotificaciones(Mensaje mensaje) {
        EnviadorNotificaciones enviadorNotificaciones = EnviadorNotificaciones.getInstancia();
        enviadorNotificaciones.enviarNotificaciones(this.formasNotificacion, mensaje, this);
    }
}
