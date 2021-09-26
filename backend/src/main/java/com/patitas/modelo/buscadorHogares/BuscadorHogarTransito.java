package com.patitas.modelo.buscadorHogares;


import com.patitas.modelo.Especie;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

public class BuscadorHogarTransito {

    private static BuscadorHogarTransito instancia;
    private WebClient webClient;

    private BuscadorHogarTransito() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.refugiosdds.com.ar/api")
                .build();
    }

    public static BuscadorHogarTransito getInstancia() {
        if(instancia == null) {
            instancia = new BuscadorHogarTransito();
        }
        return instancia;
    }

    private List<HogarTransito> buscarHogares(int pagina) {
        RespuestaHogarTransito respuesta = this.webClient.get()
                .uri("/hogares?offset=" + pagina)
                .header("Authorization", "Bearer Od1vSHElwBpWgeIv8NGNYbiUdUyFRi4dlcpNka4le4HxlSAtqNAf3qj4IWm1")
                .retrieve()
                .bodyToMono(RespuestaHogarTransito.class)
                .block();

        System.out.println("Total: " + respuesta.getTotal());
        System.out.println("Offset: " + respuesta.getOffset());

        return respuesta.getHogares();
    }

    private boolean aceptaEspecie(Especie especie, Admisiones admisiones) {
        if(especie.equals(Especie.Perro) && admisiones.isPerros()) return true;
        if(especie.equals(Especie.Gato) && admisiones.isPerros()) return true;
        return false;
    }

    private boolean hogarAdeucuado(HogarTransito hogarTransito, MascotaPerdida mascotaPerdida) {
        // TODO verificar que el hogar este dentro del radio especificado por la mascota perdida
        if(hogarTransito.getLugares_disponibles() <= 0) {
            return false;
        }
        if(!aceptaEspecie(mascotaPerdida.getEspecie(), hogarTransito.getAdminisiones())){
            return false;
        }
        if(!hogarTransito.isPatio()) {
            if(!mascotaPerdida.getTamanio().equals(Tamanio.Chica)) {
                return false;
            }
        }
        // Dios se apiade de quien lea esto
        if(!hogarTransito.getCaracteristicas().stream().allMatch(
                caracteristica -> mascotaPerdida.getCaracteristicas().stream().anyMatch(
                        caracteristicaMascota -> caracteristicaMascota.equals(caracteristica)
                )
        )){
            return false;
        }

        // Paso todas las pruebas
        return true;
    }

    public List<HogarTransito> hogaresAdecuados(MascotaPerdida mascotaPerdida, int pagina){
        List<HogarTransito> hogares = this.buscarHogares(pagina);
        return hogares.stream().filter(hogar -> hogarAdeucuado(hogar, mascotaPerdida)).collect(Collectors.toList());
    }
}
