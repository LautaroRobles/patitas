package com.patitas.modelo.enviadorNotificaciones;

import com.patitas.modelo.Contacto;
import com.twilio.Twilio;

import java.util.List;

public class EnviadorNotificaciones {

    private static final String ACCOUNT_SID = "AC253d2f071fc04f4edd959b96175d5672";
    private static final String AUTH_TOKEN = "282e56748a794e87633cf2e16f115bd7";

    private static EnviadorNotificaciones instancia;

    private EnviadorNotificaciones() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public static EnviadorNotificaciones getInstancia() {
        if(instancia == null) {
            instancia = new EnviadorNotificaciones();
        }
        return instancia;
    }

    public void enviarNotificaciones(List<Notificacion> notificaciones, Mensaje mensaje, Contacto contacto) {
        notificaciones.forEach(notificacion -> {
            notificacion.enviar(mensaje, contacto);
        });
    }
}
