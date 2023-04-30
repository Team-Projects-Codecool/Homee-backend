package com.codecool.homee_backend.scheduler;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SchedulerRunner implements CommandLineRunner {

    private final NotificationScheduler notificationScheduler;


    public SchedulerRunner(NotificationScheduler notificationScheduler) {
        this.notificationScheduler = notificationScheduler;
    }

    @Override
    public void run(String... args) throws Exception {
        notificationScheduler.sendNotifications();
    }
}
