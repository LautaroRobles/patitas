package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@DiscriminatorValue(value="QuieroAdoptar")
public class QuieroAdoptar extends Publicacion{
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quiero_adoptar")
    private List<Caracteristica> preferencias = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quiero_adoptar")
    private List<Recomendacion> recomendaciones = new ArrayList<>();

    public void addRecomendacion(Recomendacion recomendacion) { this.recomendaciones.add(recomendacion); }
}
