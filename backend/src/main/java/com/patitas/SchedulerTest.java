package com.patitas;

import com.patitas.modelo.*;
import com.patitas.modelo.scheduler.SchedulerRecomendaciones;

import java.util.Arrays;
import java.util.Collections;

public class SchedulerTest {

    public static void main(String[] args) throws Exception {

        BaseDeDatosTest db = BaseDeDatosTest.getInstancia();

        TipoCaracteristica peso = new TipoCaracteristica();
        TipoCaracteristica color = new TipoCaracteristica();

        peso.setNombre("peso");
        color.setNombre("color");

        Caracteristica peso8kg = new Caracteristica();
        Caracteristica colorRojo = new Caracteristica();
        Caracteristica colorAzul = new Caracteristica();

        peso8kg.setTipoCaracteristica(peso);
        peso8kg.setValor("8 kilos");

        colorRojo.setTipoCaracteristica(color);
        colorRojo.setValor("rojo");

        colorAzul.setTipoCaracteristica(color);
        colorAzul.setValor("azul");

        Interesado martin = new Interesado();
        DuenioMascota javier = new DuenioMascota();
        Interesado lautaro = new Interesado();

        martin.setNombre("Martin");
        martin.setPreferencias(Arrays.asList(peso8kg, colorRojo));

        javier.setNombre("Javier");

        lautaro.setNombre("Lautaro");
        lautaro.setPreferencias(Collections.singletonList(colorAzul));


        MascotaEnAdopcion moro = new MascotaEnAdopcion();
        moro.setCaracteristicas(Arrays.asList(peso8kg, colorAzul));


        db.setPersonas(Arrays.asList(martin, javier, lautaro));
        db.setPublicaciones(Collections.singletonList(moro));

        SchedulerRecomendaciones schedulerExample = new SchedulerRecomendaciones();
        schedulerExample.comenzar();
    }
}
