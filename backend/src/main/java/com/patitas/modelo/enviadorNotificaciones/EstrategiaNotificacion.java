package com.patitas.modelo.enviadorNotificaciones;

import com.patitas.modelo.Contacto;
public abstract class EstrategiaNotificacion {
    public abstract void enviar(Mensaje mensaje, Contacto contacto);
}
