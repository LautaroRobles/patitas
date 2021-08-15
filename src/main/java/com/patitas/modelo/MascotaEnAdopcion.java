package com.patitas.modelo;
import com.patitas.modelo.enviadorNotificaciones.Mensaje;

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

    public void quieroAdoptar(Contacto contacto) {
        this.getAutor().getContactos().forEach(contactoAutor -> {
            Mensaje mensaje = new Mensaje();
            mensaje.setTitulo(contacto.getNombre() + " quiere adoptar a tu mascota");
            mensaje.setCuerpo("Hola " + contactoAutor.getNombre() + ", " +
                    contacto.getNombre() + " quiere adoptar a tu mascota \n" +
                    "Aqui estan sus datos:\n" +
                    "Email: " + contacto.getEmail() +
                    "Telefono: " + contacto.getTelefono());

            contactoAutor.enviarNotificaciones(mensaje);
        });
    }
}
