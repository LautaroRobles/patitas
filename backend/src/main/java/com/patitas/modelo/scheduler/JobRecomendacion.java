package com.patitas.modelo.scheduler;

import com.patitas.BaseDeDatosTest;
import com.patitas.modelo.*;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JobRecomendacion implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BaseDeDatosTest db = BaseDeDatosTest.getInstancia();

        // Se obtienen el listado de interesados y de publicaciones de mascotas en adopcion (De la base de datos)
        List<QuieroAdoptar> interesados = db.getPublicaciones()
                .stream()
                .filter(publicacion -> publicacion instanceof QuieroAdoptar)
                .map(publicacion -> (QuieroAdoptar) publicacion)
                .collect(Collectors.toList());

        List<MascotaEnAdopcion> publicaciones = db.getPublicaciones()
                .stream()
                .filter(publicacion -> publicacion instanceof MascotaEnAdopcion)
                .map(publicacion -> (MascotaEnAdopcion) publicacion)
                .collect(Collectors.toList());

        if(interesados.size() == 0 || publicaciones.size() == 0)
            return;

        // Por cada Interesado se obtienen las publicaciones que coinciden

        float porcentajeMinimo = 100;

        interesados.forEach(interesado -> {
            publicaciones.forEach(publicacion -> {

                // Verificar que esta publicacion no haya sido recomendada al interesado antes
                if(interesado.getRecomendaciones()
                        .stream()
                        .map(recomendacion -> (Publicacion) recomendacion.getPublicacion())
                        .collect(Collectors.toList())
                        .contains(publicacion))
                    return;

                // Contamos todas las preferencias que coinciden con las caracteristicas de la publicacion
                int coinciden = 0;
                for (Caracteristica preferencia : interesado.getPreferencias()) {
                    if (publicacion.getCaracteristicas().contains(preferencia)) {
                        coinciden++;
                    }
                }
                // Se crea la recomendacion necesaria si supera el porcentaje minimo de caracteristicas que coinciden
                // y se agrega al interesado
                float porcentajeCoinciden = coinciden / (float)interesado.getPreferencias().size() * 100.0f;
                if(porcentajeCoinciden >= porcentajeMinimo) {
                    Recomendacion recomendacion = new Recomendacion();
                    recomendacion.setFecha(new Date());
                    recomendacion.setPublicacion(publicacion);

                    interesado.addRecomendacion(recomendacion);
                }
            });
        });
    }
}
