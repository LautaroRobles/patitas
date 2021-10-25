package com.patitas.scheduler;

import com.patitas.daos.DaoPublicacion;
import com.patitas.daos.DaoRecomendacion;
import com.patitas.modelo.*;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobRecomendacion implements Job {

    @Autowired
    private DaoPublicacion daoPublicacion;
    @Autowired
    private DaoRecomendacion daoRecomendacion;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // Se obtienen el listado de interesados y de publicaciones de mascotas en adopcion (De la base de datos)
        List<QuieroAdoptar> interesados = daoPublicacion.findAllQuieroAdoptar();

        List<MascotaEnAdopcion> publicaciones = daoPublicacion.findAllMascotaEnAdopcion();

        if(interesados.size() == 0 || publicaciones.size() == 0)
            return;

        // Por cada Interesado se obtienen las publicaciones que coinciden

        float porcentajeMinimo = 100;

        interesados.forEach(interesado -> {
            publicaciones.forEach(publicacion -> {

                // Verificar que esta publicacion no haya sido recomendada al interesado antes
                if(interesado.getRecomendaciones().stream().anyMatch(recomendacion -> recomendacion.getPublicacion().getId().equals(publicacion.getId())))
                    return;

                // Contamos todas las preferencias que coinciden con las caracteristicas de la publicacion
                int coinciden = 0;
                for (Caracteristica preferencia : interesado.getPreferencias()) {
                    if (publicacion.getCaracteristicas().stream().anyMatch(caracteristica -> {
                            String tipoCaracteristica = caracteristica.getTipoCaracteristica().getNombre();
                            String tipoPreferencia = preferencia.getTipoCaracteristica().getNombre();
                            String valorCaracteristica = caracteristica.getValor();
                            String valorPreferencia = preferencia.getValor();

                            if(tipoCaracteristica == null || tipoPreferencia == null || valorCaracteristica == null || valorPreferencia == null)
                                return false;

                            return tipoPreferencia.equals(tipoCaracteristica) && valorPreferencia.equals(valorCaracteristica);
                        })) {
                        coinciden++;
                    }
                }
                // Se crea la recomendacion necesaria si supera el porcentaje minimo de caracteristicas que coinciden
                // y se agrega al interesado
                float porcentajeCoinciden = coinciden / (float)interesado.getPreferencias().size() * 100.0f;
                if(porcentajeCoinciden >= porcentajeMinimo) {
                    Recomendacion nuevaRecomendacion = new Recomendacion();
                    nuevaRecomendacion.setFecha(new Date());
                    nuevaRecomendacion.setPublicacion(publicacion);

                    nuevaRecomendacion = daoRecomendacion.save(nuevaRecomendacion);

                    interesado.addRecomendacion(nuevaRecomendacion);

                    daoPublicacion.save(interesado);
                }
            });
        });
    }
}
