package com.patitas.modelo;

import java.util.ArrayList;
import java.util.List;

public class Interesado extends Persona{
    private List<Caracteristica> preferencias = new ArrayList<>();
    private List<Recomendacion> recomendaciones = new ArrayList<>();


    public List<Caracteristica> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<Caracteristica> preferencias) {
        this.preferencias = preferencias;
    }

    public List<Recomendacion> getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(List<Recomendacion> recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public void addRecomendacion(Recomendacion recomendacion) { this.recomendaciones.add(recomendacion); }
}
