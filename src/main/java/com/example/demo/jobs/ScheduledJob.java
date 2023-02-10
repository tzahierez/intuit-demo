package com.example.demo.jobs;

import com.example.demo.services.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class ScheduledJob {

    @Autowired
    private CrmService crmService;

    // TODO - get 4 hours from configuration
    @Scheduled(fixedRate = 1000 * 60 * 60 * 4, initialDelay = 1)
    public void scheduleFixedRateTask() {
        System.out.println("ScheduledJob started - " + System.currentTimeMillis() / 1000);
        crmService.onDemandAggregationAccordingExternalSystems();
        System.out.println("ScheduledJob ended - " + System.currentTimeMillis() / 1000);
    }

}
