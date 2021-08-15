package com.patitas;

import com.patitas.modelo.Persona;
import com.patitas.modelo.Publicacion;
import com.patitas.modelo.buscadorHogares.BuscadorHogarTransito;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatosTest {
    private static BaseDeDatosTest instancia;
    private List<Publicacion> publicaciones = new ArrayList<>();
    private List<Persona> personas = new ArrayList<>();


    private BaseDeDatosTest() {}

    public static BaseDeDatosTest getInstancia() {
        if(instancia == null) {
            instancia = new BaseDeDatosTest();
        }
        return instancia;
    }

    private static void setInstancia() {}

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}
