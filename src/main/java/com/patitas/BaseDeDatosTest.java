package com.patitas;

import com.patitas.modelo.Publicacion;
import com.patitas.modelo.buscadorHogares.BuscadorHogarTransito;

import java.util.List;

public class BaseDeDatosTest {
    private static BaseDeDatosTest instancia;
    private List<Publicacion> publicaciones;
    // Agregar cuando existan las personas
    // private List<Persona> personas;


    private BaseDeDatosTest() {}

    public static BaseDeDatosTest getInstancia() {
        if(instancia == null) {
            instancia = new BaseDeDatosTest();
        }
        return instancia;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }
}
