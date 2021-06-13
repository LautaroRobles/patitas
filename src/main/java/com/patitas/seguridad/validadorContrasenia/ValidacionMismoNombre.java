package com.patitas.seguridad.validadorContrasenia;

public class ValidacionMismoNombre extends ValidacionContrasenia{
    public Boolean validarContrasenia(String password, String username, String email)
    {
        return !password.equals(username) && !password.equals(email);
    }
}
