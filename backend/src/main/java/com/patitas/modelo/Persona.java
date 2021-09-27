package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private String tipoDeDocumento;
    private String nroDocumento;
    private String direccion;
    @Transient
    private List<Contacto> contactos;
    @Transient
    private List<Mascota> mascota;
}
