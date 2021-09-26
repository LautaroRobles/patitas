package com.patitas.modelo;
import com.patitas.modelo.enviadorNotificaciones.Mensaje;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MascotaEnAdopcion extends Publicacion{
    private List<Respuesta> respuestas;
    private List<Caracteristica> caracteristicas;

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
