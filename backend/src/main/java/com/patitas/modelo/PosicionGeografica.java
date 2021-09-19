package com.patitas.modelo;

public class PosicionGeografica {
    private String direccion;
    private float latitud;
    private float longitud;

    public float getLatitud() {
        return latitud;
    }

    public void setLat(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLong(float longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
