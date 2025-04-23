package com.app.home.scheduler;

import com.app.home.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class yearlyUpdateScheduler {
    @Autowired
    ApplianceService applianceService;

    @Scheduled(cron = "0 0 1 1 1 *")
    public void runYearlyUpdate(){
        System.out.println("Running yearly update.....");
        applianceService.turnOffAll();
        System.out.println("Yearly update Completed......");
    }
}
