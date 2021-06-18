package com.patitas.modelo.buscadorHogares;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

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

    public List<HogarTransito> buscarHogarTransito(int pagina) {
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
}
