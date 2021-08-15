package com.patitas;

import com.patitas.modelo.Contacto;
import com.patitas.modelo.buscadorHogares.BuscadorHogarTransito;
import com.patitas.modelo.buscadorHogares.HogarTransito;
import com.patitas.modelo.enviadorNotificaciones.*;

import java.util.ArrayList;
import java.util.List;

public class TwilioTest {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC253d2f071fc04f4edd959b96175d5672";
    public static final String AUTH_TOKEN = "282e56748a794e87633cf2e16f115bd7";
    public static final String SENDGRID_API_KEY = "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";

    public static void main(String args[]) {
        Notificaciones();
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
        Mensaje mensajeCarol = new Mensaje();
        mensajeCarol.setTitulo("Encontramos a tu mascota");
        mensajeCarol.setCuerpo("Hola " + contactoCarol.getNombre() + ", encontramos a tu mascota");

        Mensaje mensajeLautaro = new Mensaje();
        mensajeLautaro.setTitulo("Encontramos a tu mascota");
        mensajeLautaro.setCuerpo("Hola " + contactoLautaro.getNombre() + ", encontramos a tu mascota");

        contactoCarol.enviarNotificaciones(mensajeCarol);
        contactoLautaro.enviarNotificaciones(mensajeLautaro);
    }
}
