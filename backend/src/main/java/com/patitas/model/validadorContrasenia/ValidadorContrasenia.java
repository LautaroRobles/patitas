package com.patitas.model.validadorContrasenia;

import java.util.List;

public class ValidadorContrasenia {
    public List<ValidacionContrasenia> getValidaciones() {
        return validaciones;
    }

    public void setValidaciones(List<ValidacionContrasenia> validaciones) {
        this.validaciones = validaciones;
    }

    private List<ValidacionContrasenia> validaciones;

    public Boolean validarContrasenia(String password, String username, String email)
    {
        return validaciones.stream().allMatch(
                validacion -> validacion.validarContrasenia(password, username, email)
        );
    }
}
