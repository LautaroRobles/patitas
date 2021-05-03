package model.validadorContrasenia;

import java.util.List;

public class ValidadorContrasenia {
    public List<ValidacionContrasenia> getValidaciones() {
        return validaciones;
    }

    public void setValidaciones(List<ValidacionContrasenia> validaciones) {
        this.validaciones = validaciones;
    }

    private List<ValidacionContrasenia> validaciones;

    public Boolean validarContrasenia(String contrasenia)
    {
        Boolean contraseniaValida = validaciones.stream().allMatch(
                validacion -> validacion.validarContrasenia(contrasenia)
        );
        return contraseniaValida;
    }
}
