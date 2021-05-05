package model;

import com.patitas.model.validadorContrasenia.ValidacionContrasenia;
import com.patitas.model.validadorContrasenia.ValidacionLongitud;
import com.patitas.model.validadorContrasenia.ValidacionPeoresContrasenias;
import com.patitas.model.validadorContrasenia.ValidadorContrasenia;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ContraseniaTest {
    private ValidadorContrasenia validador = new ValidadorContrasenia();

    @Before
    public void inicializarValidador()
    {
        // Creamos las validaciones y las guardamos en una lista de validaciones
        List<ValidacionContrasenia> validaciones = new ArrayList<>();
        ValidacionLongitud validacionLongitud = new ValidacionLongitud();
        ValidacionPeoresContrasenias validacionPeoresContrasenias = new ValidacionPeoresContrasenias();

        validaciones.add(validacionLongitud);
        validaciones.add(validacionPeoresContrasenias);
        // Establecemos al validador las validaciones que debe usar
        validador.setValidaciones(validaciones);
    }
    @Test
    public void contraseniaCorta()
    {
        Assert.assertFalse(validador.validarContrasenia("cont"));
        Assert.assertTrue(validador.validarContrasenia("contrasenia"));
    }
    @Test
    public void contraseniaEnElTop10000()
    {
        Assert.assertFalse(validador.validarContrasenia("qwertyuiop"));
        Assert.assertFalse(validador.validarContrasenia("1234567890"));
        Assert.assertTrue(validador.validarContrasenia("contrasenia_segura_y_larga"));
    }
}
