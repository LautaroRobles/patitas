package com.patitas.model;

import java.util.List;
import java.util.Date;

public class Persona {
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private String tipoDocumento;
    private int nroDocumento;
    private List<Contacto> contactos;
    private List<Mascota> mascotas;
}

