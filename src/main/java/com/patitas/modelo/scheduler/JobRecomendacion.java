package com.patitas.modelo.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobRecomendacion implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("ejecutando");

        // Se obtienen el listado de interesados y de publicaciones de mascotas en adopcion

        // Por cada Interesado se obtienen las publicaciones que coinciden

        // Se crean las recomendaciones necesarias y se agregan al interesado
    }
}
