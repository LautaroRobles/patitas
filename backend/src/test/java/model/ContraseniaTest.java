package model;

import com.patitas.seguridad.validadorContrasenia.*;
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
        ValidacionMismoNombre validacionMismoNombre = new ValidacionMismoNombre();

        validacionLongitud.setLongitudMinima(8);

        validaciones.add(validacionLongitud);
        validaciones.add(validacionPeoresContrasenias);
        validaciones.add(validacionMismoNombre);
        // Establecemos al validador las validaciones que debe usar
        validador.setValidaciones(validaciones);
    }
    /*
    @Test
    public void contraseniaCorta()
    {
        Assert.assertFalse(validador.validarContrasenia("cont","",""));
        Assert.assertTrue(validador.validarContrasenia("contrasenia","",""));
    }
    @Test
    public void contraseniaEnElTop10000()
    {
        Assert.assertFalse(validador.validarContrasenia("qwertyuiop","",""));
        Assert.assertFalse(validador.validarContrasenia("1234567890","",""));
        Assert.assertTrue(validador.validarContrasenia("contrasenia_segura_y_larga","",""));
    }
    @Test
    public void contraseniaMismoNombre()
    {
        Assert.assertTrue(validador.validarContrasenia("contraseniasegura","lautaro","lrobles@frba.utn.edu.ar"));
        Assert.assertFalse(validador.validarContrasenia("tomygabutti@gmail.com","tomy","tomygabutti@gmail.com"));
        Assert.assertFalse(validador.validarContrasenia("jose123456","jose123456","tomygabutti@gmail.com"));
    }
    */
}
