package com.patitas.modelo.enviadorNotificaciones;

import com.patitas.modelo.Contacto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Whatsapp extends Notificacion{
    public void enviar(String mensaje, Contacto contacto) {
        PhoneNumber from = new PhoneNumber("whatsapp:+14155238886");
        PhoneNumber to   = new PhoneNumber("whatsapp:"+contacto.getTelefono());

        Message message = Message.creator(
                to,
                from,
                mensaje)
                .create();

        System.out.println(message.getSid());
    }
}
