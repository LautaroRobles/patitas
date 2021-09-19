package com.patitas.modelo.enviadorNotificaciones;

import com.patitas.modelo.Contacto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS extends Notificacion{
    public void enviar(Mensaje mensaje, Contacto contacto) {
        PhoneNumber from = new PhoneNumber("+18329816082");
        PhoneNumber to   = new PhoneNumber(contacto.getTelefono());

        Message message = Message.creator(
                to,
                from,
                mensaje.getCuerpo())
                .create();

        System.out.println(message.getSid());
    }
}
