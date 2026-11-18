package pl.edu.agh.to.school.notification;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.student.Student;

@Profile("test")
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

    @Override
    public void notify(Student student, Grade grade) {
        System.out.printf("%s got degree: %s from: %s%n", student.getFullName(), grade.value(), grade.course());
    }
}
