package com.patitas.modelo;

public class Foto {
    private int ancho;
    private int alto;
    private String calidad;
    private FormatoImagen formato;

    // TODO definir normalizar
    public void normalizar() {}

    // Getters Setters
    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public FormatoImagen getFormato() {
        return formato;
    }

    public void setFormato(FormatoImagen formato) {
        this.formato = formato;
    }
}
