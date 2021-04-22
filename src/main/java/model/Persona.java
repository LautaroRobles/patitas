package model;

public class Persona {
    private String nombre;
    private String contrasenia;

    public Boolean contraseniaValida()
    {
        return contrasenia.length() > 8;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
