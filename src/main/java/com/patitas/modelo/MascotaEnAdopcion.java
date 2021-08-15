package com.patitas.modelo;
import java.util.List;

public class MascotaEnAdopcion extends Publicacion{
    private List<Respuesta> respuestas;
    private List<Caracteristica> caracteristicas;

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
