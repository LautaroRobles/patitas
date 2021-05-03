package model;

import model.validadorContrasenia.ValidacionContrasenia;
import model.validadorContrasenia.ValidacionLongitud;
import model.validadorContrasenia.ValidacionPeoresContrasenias;
import model.validadorContrasenia.ValidadorContrasenia;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ContraseniaTest {
    @Test
    public void contraseniaCorta()
    {
        // Creamos validador de contrasenia
        ValidadorContrasenia validador = new ValidadorContrasenia();

        // Creamos las validaciones y las guardamos en una lista de validaciones
        ValidacionLongitud validacionLongitud = new ValidacionLongitud();
        ValidacionPeoresContrasenias validacionPeoresContrasenias = new ValidacionPeoresContrasenias();
        List<ValidacionContrasenia> validaciones = new ArrayList<>();
        validaciones.add(validacionLongitud);
        validaciones.add(validacionPeoresContrasenias);

        // Establecemos al validador las validaciones que debe usar
        validador.setValidaciones(validaciones);

        Assert.assertFalse(validador.validarContrasenia("cont"));
        Assert.assertTrue(validador.validarContrasenia("contrasenia"));
        Assert.assertFalse(validador.validarContrasenia("qwertyuiop"));
    }
}
