package com.patitas.modelo;
import com.patitas.modelo.enviadorNotificaciones.Mensaje;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@DiscriminatorValue(value="MascotaEnAdopcion")
public class MascotaEnAdopcion extends Publicacion{
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "id_mascota_en_adopcion")
    private List<Respuesta> respuestas;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "id_mascota_en_adopcion")
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
