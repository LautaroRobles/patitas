package com.patitas.modelo.scheduler;

import com.patitas.BaseDeDatosTest;
import com.patitas.modelo.MascotaEnAdopcion;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

public class JobRecomendacion implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        BaseDeDatosTest db = BaseDeDatosTest.getInstancia();
        System.out.println("ejecutando");

        // Se obtienen el listado de interesados y de publicaciones de mascotas en adopcion (De la base de datos)
        // Incompleto
        // List<MascotaEnAdopcion> publicaciones = db.getPublicaciones().stream().filter();


        // Por cada Interesado se obtienen las publicaciones que coinciden

        // Se crean las recomendaciones necesarias y se agregan al interesado
    }
}
