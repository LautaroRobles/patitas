package com.patitas.modelo;

import com.patitas.modelo.enviadorNotificaciones.EnviadorNotificaciones;
import com.patitas.modelo.enviadorNotificaciones.Mensaje;
import com.patitas.modelo.enviadorNotificaciones.Notificacion;

import java.util.List;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Notificacion> getFormasNotificacion() {
        return formasNotificacion;
    }

    public void setNotificacion(List<Notificacion> formasNotificacion) {
        this.formasNotificacion = formasNotificacion;
    }
}
