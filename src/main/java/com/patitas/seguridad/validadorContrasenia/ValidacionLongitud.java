package com.patitas.seguridad.validadorContrasenia;

public class ValidacionLongitud extends ValidacionContrasenia{
    private int longitudMinima;
    public Boolean validarContrasenia(String password, String username, String email)
    {
        return password.length() > longitudMinima;
    }

    public int getLongitudMinima() {
        return longitudMinima;
    }

    public void setLongitudMinima(int longitudMinima) {
        this.longitudMinima = longitudMinima;
    }
}

