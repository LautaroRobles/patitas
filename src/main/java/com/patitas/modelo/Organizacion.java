package com.patitas.modelo;

public class Organizacion {
    private String nombre;
    private String direccion;
    private List<TipoCaracteristica> caracteristicasDisponibles;
    private List<Publicacion>publicaciones;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
}


