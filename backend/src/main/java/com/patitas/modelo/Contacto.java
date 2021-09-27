package com.patitas.modelo;

import com.patitas.modelo.enviadorNotificaciones.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Entity
@Table
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    // TODO recordar hablar sobre esto en las justificaciones
    @ElementCollection
    private List<TipoNotificacion> tipoNotificaciones;
    @Transient
    private List<Notificacion> formasNotificacion;

    public void setTipoNotificaciones(List<TipoNotificacion> tipoNotificaciones) {
        this.tipoNotificaciones = tipoNotificaciones;
        formasNotificacion = tipoNotificaciones.stream().map(tipoNotificacion -> {
            switch (tipoNotificacion) {
                case SMS: return new SMS();
                case Whatsapp: return new Whatsapp();
                case Email: return new Email();
                default: return null;
            }
        }).collect(Collectors.toList());
    }

    public void enviarNotificaciones(Mensaje mensaje) {
        EnviadorNotificaciones enviadorNotificaciones = EnviadorNotificaciones.getInstancia();
        enviadorNotificaciones.enviarNotificaciones(this.formasNotificacion, mensaje, this);
    }
}
