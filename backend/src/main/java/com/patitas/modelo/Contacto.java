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
    @ElementCollection              // Listado de elementos simples (o sea enums)
    @Enumerated(EnumType.STRING)    // Para que aparezcan los enums como varchar en la base de datos
    private List<TipoNotificacion> tipoNotificaciones;

    // Ya elimine el formas de notificacion

    public void enviarNotificaciones(Mensaje mensaje) {
        EnviadorNotificaciones enviadorNotificaciones = EnviadorNotificaciones.getInstancia();
        enviadorNotificaciones.enviarNotificaciones(this.tipoNotificaciones, mensaje, this);
    }
}
