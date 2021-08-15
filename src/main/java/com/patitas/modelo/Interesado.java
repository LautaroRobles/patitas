package com.patitas.modelo;

import java.util.List;

public class Interesado extends Persona{
    private List<Caracteristica> preferencias;
    private List<Recomendacion> recomendacions;


    public List<Caracteristica> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<Caracteristica> preferencias) {
        this.preferencias = preferencias;
    }

    public List<Recomendacion> getRecomendacions() {
        return recomendacions;
    }

    public void setRecomendacions(List<Recomendacion> recomendacions) {
        this.recomendacions = recomendacions;
    }
}
