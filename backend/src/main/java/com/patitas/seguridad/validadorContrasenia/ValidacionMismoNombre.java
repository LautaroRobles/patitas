package com.patitas.seguridad.validadorContrasenia;

import com.patitas.excepciones.InvalidPasswordException;

public class ValidacionMismoNombre extends ValidacionContrasenia{
    public Boolean validarContrasenia(String password, String username, String email) throws InvalidPasswordException {
        boolean contraseniaValida = !password.equals(username) && !password.equals(email);

        if(!contraseniaValida)
            throw new InvalidPasswordException("La contraseña no puede contener el username o el mail");

        return true;
    }
}
