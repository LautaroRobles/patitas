package com.patitas.modelo;

import java.util.Date;
import java.util.List;


public class DuenioMascota extends Persona{
    private List<Mascota> mascotas;

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}

