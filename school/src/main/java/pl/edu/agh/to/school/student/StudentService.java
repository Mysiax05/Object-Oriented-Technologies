package pl.edu.agh.to.school.student;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.grade.GradeService;

@Service
public class StudentService {

    GradeService gradeService;

    public StudentService(GradeService gradeService) {
        this.gradeService = gradeService;
        System.out.println("Student service created");
    }

    @PostConstruct
    public void onServiceStarted() {
        System.out.println("Student service started");
    }

    @PreDestroy
    public void onServiceDestroy() {
        System.out.println("Student service destroyed");
    }
}
