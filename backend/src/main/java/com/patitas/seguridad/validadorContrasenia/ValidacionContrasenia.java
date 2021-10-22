package com.patitas.seguridad.validadorContrasenia;

import com.patitas.excepciones.InvalidPasswordException;

public abstract class ValidacionContrasenia {
    public abstract Boolean validarContrasenia(String password, String username, String email) throws InvalidPasswordException;
}
