package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class QuieroAdoptar extends Publicacion{
    private List<Caracteristica> preferencias = new ArrayList<>();
    private List<Recomendacion> recomendaciones = new ArrayList<>();

    public void addRecomendacion(Recomendacion recomendacion) { this.recomendaciones.add(recomendacion); }
}
