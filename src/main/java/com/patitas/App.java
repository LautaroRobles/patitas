package com.patitas;

import com.patitas.modelo.buscadorHogares.BuscadorHogarTransito;
import com.patitas.modelo.buscadorHogares.HogarTransito;

import java.util.List;

public class App {
    public static void main(String args[]) {
        BuscadorHogarTransito buscador = BuscadorHogarTransito.getInstancia();

        List<HogarTransito> hogares = buscador.buscarHogarTransito(1);

        System.out.println("Hogares!");
        hogares.forEach(hogar -> {
            System.out.println("{");
            System.out.println("nombre: " + hogar.getNombre());
            System.out.println("ubicacion");
            System.out.println("direccion: " + hogar.getUbicacion().getDireccion());
            System.out.println("lat: " + hogar.getUbicacion().getLatitud());
            System.out.println("long: " + hogar.getUbicacion().getLongitud());
            System.out.println("}");
        });
    }
}
