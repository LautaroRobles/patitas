package model;

import org.junit.Assert;
import org.junit.Test;

public class PersonaTest {

    @Test
    public void contraseniaValidaYNoValida()
    {
        Persona lautaro = new Persona();
        lautaro.setNombre("Lautaro");
        lautaro.setContrasenia("ooooeeeeeoooo");

        Persona lucas = new Persona();
        lucas.setNombre("Lucas");
        lucas.setContrasenia("1234");

        // La contraseña de lautaro es valida pero la de lucas no
        Assert.assertTrue(lautaro.contraseniaValida());
        Assert.assertFalse(lucas.contraseniaValida());
    }
}
