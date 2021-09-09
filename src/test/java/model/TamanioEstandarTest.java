package model;

import com.patitas.modelo.Foto;
import com.patitas.modelo.Organizacion;
import com.patitas.modelo.Tamanio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TamanioEstandarTest {

    private Organizacion organizacion;
    private Foto foto;

    @Before
    public void init() {

        Tamanio tamanioEstandar = new Tamanio();
        tamanioEstandar.setAlto(800);
        tamanioEstandar.setAncho(800);

        this.organizacion = new Organizacion();
        this.organizacion.setTamanioEstandar(tamanioEstandar);

        Tamanio tamanio = new Tamanio();
        tamanio.setAlto(400);
        tamanio.setAncho(600);

        this.foto = new Foto();
        this.foto.setTamanio(tamanio);
    }

    @Test
    public void normalizarFoto() {
        this.foto.normalizar(this.organizacion.getTamanioEstandar());
        Assert.assertEquals(800, this.foto.getTamanio().getAlto());
        Assert.assertEquals(800, this.foto.getTamanio().getAncho());
    }
}
