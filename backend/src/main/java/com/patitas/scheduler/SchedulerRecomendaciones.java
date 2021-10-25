package com.patitas.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

@Component
public class SchedulerRecomendaciones {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(JobRecomendacion.class)
                .withIdentity("Recomendaciones")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("TriggerRecomendaciones")
                .withSchedule(
                        // Scheduler que se ejecuta cada lunes ?
                        // DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().onDaysOfTheWeek(1)
                        // Scheduler que se ejecuta cada 10 segundos
                        SimpleScheduleBuilder.repeatSecondlyForever(10)
                )
                .build();
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job, StdSchedulerFactory factory)
            throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        return scheduler;
    }

    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job, SpringBeanJobFactory springBeanJobFactory) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        schedulerFactory.setJobFactory(springBeanJobFactory);
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);
        return schedulerFactory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    /*
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
                        // DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().onDaysOfTheWeek(1)
                        // Scheduler que se ejecuta cada 10 segundos
                        SimpleScheduleBuilder.repeatSecondlyForever(10)
                )
                .build();

        // Le asignamos al scheduler el trabajo y lo iniciamos
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
    */
}
