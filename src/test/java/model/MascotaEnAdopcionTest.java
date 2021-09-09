package model;

import com.patitas.modelo.*;
import com.patitas.modelo.enviadorNotificaciones.Whatsapp;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MascotaEnAdopcionTest {

    private DuenioMascota duenioMascota;
    private DuenioMascota interesado;
    private MascotaEnAdopcion publicacion;

    @Before
    public void init() {
        this.duenioMascota = new DuenioMascota();
        this.duenioMascota.setNombre("Javier");
        this.duenioMascota.setApellido("Blanco");

        Contacto contactoTomi = new Contacto();
        contactoTomi.setNombre("Tomi");
        contactoTomi.setApellido("Gabutti");
        contactoTomi.setTelefono("+5491141882877");
        contactoTomi.setNotificacion(Arrays.asList(new Whatsapp()));

        Contacto contactoJose = new Contacto();
        contactoJose.setNombre("Jose");
        contactoJose.setApellido("Barreto");
        contactoJose.setTelefono("+5491138814218");
        contactoJose.setNotificacion(Arrays.asList(new Whatsapp()));

        Contacto contactoLau = new Contacto();
        contactoLau.setNombre("Lautaro");
        contactoLau.setApellido("Robles");
        contactoLau.setTelefono("+5491123121301");
        contactoLau.setNotificacion(Arrays.asList(new Whatsapp()));

        this.duenioMascota.setContactos(Arrays.asList(contactoTomi, contactoJose, contactoLau));

        this.interesado = new DuenioMascota();
        this.interesado.setNombre("Lucas");
        this.interesado.setApellido("Robaina");

        Contacto contactoLucas = new Contacto();
        contactoLucas.setNombre("Lucas");
        contactoLucas.setApellido("Robaina");
        contactoLucas.setEmail("email@blablabla.com");
        contactoLucas.setTelefono("+591123456789");

        this.interesado.setContactos(Arrays.asList(contactoLucas));

        Mascota mascota = new Mascota();

        mascota.setNombre("Firulais");
        mascota.setApodo("Firu");
        mascota.setEspecie(Especie.Perro);
        mascota.setEdad(7);

        this.duenioMascota.setMascotas(Arrays.asList(mascota));

        this.publicacion = new MascotaEnAdopcion();
        this.publicacion.setTitulo("Mascota en Adopción");
        this.publicacion.setCuerpo("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum at pellentesque risus, a sodales lectus.");
        this.publicacion.setAutor(this.duenioMascota);
        this.publicacion.setAprobada(true);
    }

    @Test
    public void interesadoQuiereAdoptarMascotaDeDuenio() {
        this.publicacion.quieroAdoptar(this.interesado.getContactos().get(0));
    }
}
