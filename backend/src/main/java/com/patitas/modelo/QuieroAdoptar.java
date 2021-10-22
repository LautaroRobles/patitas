package com.patitas.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@DiscriminatorValue(value="QuieroAdoptar")
public class QuieroAdoptar extends Publicacion{
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "id_quiero_adoptar")
    private List<Caracteristica> preferencias;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "id_quiero_adoptar")
    private List<Recomendacion> recomendaciones;

    public void addRecomendacion(Recomendacion recomendacion) {
        if(this.recomendaciones == null) {
            this.recomendaciones = new ArrayList<>();
        }
        this.recomendaciones.add(recomendacion);
    }
}
