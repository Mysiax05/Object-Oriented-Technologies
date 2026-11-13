package pl.edu.agh.to.school.notification;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class ConsoleNotificationService implements NotificationService{

    public ConsoleNotificationService() {
        System.out.println("Console notification service created");
    }

    @PostConstruct
    public void onServiceStarted() {
        System.out.println("Console notification service started");
    }

    @PreDestroy
    public void onServiceDestroy() {
        System.out.println("Console notification service destroyed");
    }
}
