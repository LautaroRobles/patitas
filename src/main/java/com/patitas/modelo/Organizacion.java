package com.patitas.modelo;

import java.util.List;

public class Organizacion {
    private String nombre;
    private PosicionGeografica ubicacion;
    private String email;
    private List<TipoCaracteristica> caracteristicasDisponibles;
    private List<Publicacion>publicaciones;
    private Tamanio tamanioEstandar;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<TipoCaracteristica> getCaracteristicasDisponibles() {
        return caracteristicasDisponibles;
    }

    public void setCaracteristicasDisponibles(List<TipoCaracteristica> caracteristicasDisponibles) {
        this.caracteristicasDisponibles = caracteristicasDisponibles;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public PosicionGeografica getUbicacion() { return ubicacion; }

    public void setUbicacion(PosicionGeografica ubicacion) { this.ubicacion = ubicacion; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Tamanio getTamanioEstandar() { return tamanioEstandar; }

    public void setTamanioEstandar(Tamanio tamanioEstandar) { this.tamanioEstandar = tamanioEstandar; }
}


