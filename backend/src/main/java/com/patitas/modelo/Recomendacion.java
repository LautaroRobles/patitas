package com.patitas.modelo;

import java.util.Date;

public class Recomendacion {
    private MascotaEnAdopcion publicacion;
    private Date fecha;

    public MascotaEnAdopcion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(MascotaEnAdopcion publicacion) {
        this.publicacion = publicacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
