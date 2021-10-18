package com.patitas.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class RegistrarPersonaDTO {
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private String tipoDeDocumento;
    private String nroDocumento;
    private String direccion;
    private List<RegistrarContactoDTO> contactos;
}
