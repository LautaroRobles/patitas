package com.patitas.model.validadorContrasenia;

public abstract class ValidacionContrasenia {
    public abstract Boolean validarContrasenia(String password, String username, String email);
}