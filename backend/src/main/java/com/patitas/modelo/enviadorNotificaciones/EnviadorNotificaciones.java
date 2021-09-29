package com.patitas.modelo.enviadorNotificaciones;

import com.patitas.modelo.Contacto;
import com.twilio.Twilio;

import java.util.List;
import java.util.stream.Collectors;

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

    public void enviarNotificaciones(List<TipoNotificacion> tiposNotificaciones, Mensaje mensaje, Contacto contacto) {

        List<EstrategiaNotificacion> estrategiasNotificacion = tiposNotificaciones.stream().map(tipoNotificacion -> {
            switch (tipoNotificacion) {
                case SMS: return new SMS();
                case Whatsapp: return new Whatsapp();
                case Email: return new Email();
                default: return null;
            }
        }).collect(Collectors.toList());

        estrategiasNotificacion.forEach(estrategiaNotificacion -> {
            estrategiaNotificacion.enviar(mensaje, contacto);
        });
    }
}
