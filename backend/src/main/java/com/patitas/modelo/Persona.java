package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Persona {
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private String tipoDeDocumento;
    private String nroDocumento;
    private String direccion;
    private List<Contacto> contactos;
    private List<Mascota> mascota;
}
