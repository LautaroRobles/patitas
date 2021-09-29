package com.patitas.seguridad;

import com.patitas.modelo.Organizacion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Usuario {
    private String username;
    private String password;
    private String email;
    private List<Organizacion> organizaciones;
    private Rol rol;
}
