package model.validadorContrasenia;

public class ValidacionLongitud extends ValidacionContrasenia{
    public Boolean validarContrasenia(String contrasenia)
    {
        return contrasenia.length() > 8;
    }
}

