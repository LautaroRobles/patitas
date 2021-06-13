package com.patitas.modelo;

import java.util.List;

public class MascotaPerdida {
    private List<Foto> foto;
    private String descripcion;
    private PosicionGeografica lugarEncntrada;

    public List<Foto> getFoto() {
        return foto;
    }

    public void setFoto(List<Foto> foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PosicionGeografica getLugarEncntrada() {
        return lugarEncntrada;
    }

    public void setLugarEncntrada(PosicionGeografica lugarEncntrada) {
        this.lugarEncntrada = lugarEncntrada;
    }
}
