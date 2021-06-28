package com.patitas.modelo;

public class Foto {
    private Tamanio tamanio;
    private String calidad;
    private FormatoImagen formato;

    // TODO definir normalizar
    public void normalizar() {}

    // Getters Setters

    public Tamanio getTamanio() { return tamanio; }

    public void setTamanio(Tamanio tamanio) { this.tamanio = tamanio; }

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
