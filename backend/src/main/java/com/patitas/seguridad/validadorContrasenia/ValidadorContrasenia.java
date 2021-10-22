package com.patitas.seguridad.validadorContrasenia;

import com.patitas.excepciones.InvalidPasswordException;

import java.util.List;

public class ValidadorContrasenia {

    private List<ValidacionContrasenia> validaciones;

    public Boolean validarContrasenia(String password, String username, String email) throws InvalidPasswordException
    {
        boolean contraseniaValida = true;

        for(int i = 0; i < validaciones.size(); i++) {
            contraseniaValida = contraseniaValida && validaciones.get(i).validarContrasenia(password, username, email);
        }

        return contraseniaValida;
    }

    public List<ValidacionContrasenia> getValidaciones() {
        return validaciones;
    }

    public void setValidaciones(List<ValidacionContrasenia> validaciones) {
        this.validaciones = validaciones;
    }

}
