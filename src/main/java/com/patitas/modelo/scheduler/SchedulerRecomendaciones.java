package com.patitas.modelo.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.DayOfWeek;

public class SchedulerRecomendaciones {

    public void comenzar() throws SchedulerException, InterruptedException {

        // Creamos el Scheduler con el builder
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // Creamos el JobDetail con el builder
        // Esto le dice al scheduler que clase tiene la accion schedulear
        JobBuilder jobBuilder = JobBuilder.newJob(JobRecomendacion.class);
        JobDetail jobDetail = jobBuilder
                .withIdentity("Recomendaciones")
                .build();

        // Creamos el Trigger
        // Indica cuando comienza a ejecutarse y cuando se repite el scheduler
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("TriggerRecomendaciones")
                .startNow()
                .withSchedule(
                        // Scheduler que se ejecuta cada lunes ?
                        //DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().onDaysOfTheWeek(1)
                        // Scheduler que se ejecuta cada 2 segundos
                        SimpleScheduleBuilder.repeatSecondlyForever(2)
                )
                .build();

        // Le asignamos al scheduler el trabajo y lo iniciamos
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
