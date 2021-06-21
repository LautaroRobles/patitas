package com.patitas.modelo.enviadorNotificaciones;

import com.patitas.modelo.Contacto;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;

import java.io.IOException;

public class Email extends Notificacion {
    private static final String SENDGRID_API_KEY = "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";

    public void enviar(String mensaje, Contacto contacto) {
        try {
            com.sendgrid.helpers.mail.objects.Email from = new com.sendgrid.helpers.mail.objects.Email("lrobles@frba.utn.edu.ar");
            com.sendgrid.helpers.mail.objects.Email to = new com.sendgrid.helpers.mail.objects.Email(contacto.getEmail());

            String subject = "Probando";
            Content content = new Content("text/html", mensaje);

            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(SENDGRID_API_KEY);
            Request request = new Request();

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getHeaders());
            System.out.println(response.getBody());
        }
        catch (IOException exception) {
            System.out.println("ERROR: " +exception.getMessage());
        }
    }
}
