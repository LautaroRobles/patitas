package com.patitas.modelo.buscadorHogares;

import com.patitas.modelo.PosicionGeografica;

import java.util.List;

public class HogarTransito {
    private String id;
    private String nombre;
    private PosicionGeografica ubicacion;
    private String telefono;
    private Admisiones adminisiones;
    private int capacidad;
    private int lugares_disponibles;
    private boolean patio;
    private List<String> caracteristicas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PosicionGeografica getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(PosicionGeografica ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Admisiones getAdminisiones() {
        return adminisiones;
    }

    public void setAdminisiones(Admisiones adminisiones) {
        this.adminisiones = adminisiones;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getLugares_disponibles() {
        return lugares_disponibles;
    }

    public void setLugares_disponibles(int lugares_disponibles) {
        this.lugares_disponibles = lugares_disponibles;
    }

    public boolean isPatio() {
        return patio;
    }

    public void setPatio(boolean patio) {
        this.patio = patio;
    }

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
