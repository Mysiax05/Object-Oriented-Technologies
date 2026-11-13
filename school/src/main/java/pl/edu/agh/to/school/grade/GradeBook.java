package pl.edu.agh.to.school.grade;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class GradeBook {

    public GradeBook() {
        System.out.println("GradeBook service created");
    }

    @PostConstruct
    public void onServiceStarted() {
        System.out.println("GradeBook service started");
    }

    @PreDestroy
    public void onServiceDestroy() {
        System.out.println("GradeBook service destroyed");
    }
}
