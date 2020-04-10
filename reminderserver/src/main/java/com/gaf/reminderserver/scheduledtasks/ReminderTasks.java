package com.gaf.reminderserver.scheduledtasks;

import com.gaf.reminderserver.services.TenantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReminderTasks {
    @Autowired
    TenantsService tenantsService;

    @Scheduled(cron = "${cron}")
    public void sendReminders() {
        try {
            tenantsService.sendReminders();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
