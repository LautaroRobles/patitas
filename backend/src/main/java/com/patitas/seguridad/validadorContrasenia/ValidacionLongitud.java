package com.patitas.seguridad.validadorContrasenia;

import com.patitas.excepciones.InvalidPasswordException;

public class ValidacionLongitud extends ValidacionContrasenia{
    private int longitudMinima;
    public Boolean validarContrasenia(String password, String username, String email) throws InvalidPasswordException {
        boolean contraseniaValida = password.length() > longitudMinima;

        if(!contraseniaValida)
            throw new InvalidPasswordException("La contraseña tiene que contener minimo "+longitudMinima+" caracteres");

        return true;
    }

    public int getLongitudMinima() {
        return longitudMinima;
    }

    public void setLongitudMinima(int longitudMinima) {
        this.longitudMinima = longitudMinima;
    }
}

