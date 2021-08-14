package com.patitas;

import com.patitas.modelo.Contacto;
import com.patitas.modelo.buscadorHogares.BuscadorHogarTransito;
import com.patitas.modelo.buscadorHogares.HogarTransito;
import com.patitas.modelo.enviadorNotificaciones.Notificacion;
import com.patitas.modelo.enviadorNotificaciones.SMS;
import com.patitas.modelo.enviadorNotificaciones.Whatsapp;
import com.patitas.modelo.enviadorNotificaciones.Email;

import java.util.ArrayList;
import java.util.List;

public class TwilioTest {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC253d2f071fc04f4edd959b96175d5672";
    public static final String AUTH_TOKEN = "282e56748a794e87633cf2e16f115bd7";
    public static final String SENDGRID_API_KEY = "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";

    public static void main(String args[]) {
        Notificaciones();
        ApiRest();
    }
    private static void Notificaciones() {
        // Probando las notificaciones

        // Contacto de Lautaro
        Contacto contactoLautaro = new Contacto();
        contactoLautaro.setApellido("Robles");
        contactoLautaro.setNombre("Lautaro");
        contactoLautaro.setTelefono("+5491123121301");
        contactoLautaro.setEmail("lautarorobles_oro@hotmail.com");

        List<Notificacion> notificacionesPreferidasLautaro = new ArrayList<>();
        notificacionesPreferidasLautaro.add(new Whatsapp());
        notificacionesPreferidasLautaro.add(new Email());

        contactoLautaro.setNotificacion(notificacionesPreferidasLautaro);

        // Contacto de Carol
        Contacto contactoCarol = new Contacto();
        contactoCarol.setApellido("Navia");
        contactoCarol.setNombre("Carol");
        contactoCarol.setTelefono("+5491150966922");
        contactoCarol.setEmail("carolmdz2006@gmail.com");

        List<Notificacion> notificacionesPreferidasCarol = new ArrayList<>();
        notificacionesPreferidasCarol.add(new Whatsapp());
        notificacionesPreferidasCarol.add(new SMS());

        contactoCarol.setNotificacion(notificacionesPreferidasCarol);

        // Encontramos a tu mascota! Enviar notificacion
        contactoCarol.enviarNotificaciones("Te encontramos a tu mascota! " + contactoCarol.getNombre());
        contactoLautaro.enviarNotificaciones("Te encontramos a tu mascota! " + contactoLautaro.getNombre());
    }

    private static void ApiRest() {

        // Probando el buscador de hogar de transito
        BuscadorHogarTransito buscador = BuscadorHogarTransito.getInstancia();

        List<HogarTransito> hogares = buscador.buscarHogarTransito(1);

        System.out.println("Hogares!");
        hogares.forEach(hogar -> {
            System.out.println("{");
            System.out.println("nombre: " + hogar.getNombre());
            System.out.println("ubicacion");
            System.out.println("direccion: " + hogar.getUbicacion().getDireccion());
            System.out.println("lat: " + hogar.getUbicacion().getLatitud());
            System.out.println("long: " + hogar.getUbicacion().getLongitud());
            System.out.println("}");
        });
    }
}
