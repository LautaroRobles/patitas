package com.patitas.modelo.enviadorNotificaciones;

import com.patitas.modelo.Contacto;
public abstract class Notificacion {
    public abstract void enviar(Mensaje mensaje, Contacto contacto);
}
