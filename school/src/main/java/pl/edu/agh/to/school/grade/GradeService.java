package pl.edu.agh.to.school.grade;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.notification.NotificationService;
import pl.edu.agh.to.school.student.Student;

@Service
public class GradeService {

    private final GradeBook gradeBook;
    NotificationService notificationService;

    public GradeService(GradeBook gradeBook, NotificationService notificationService) {
        this.gradeBook = gradeBook;
        this.notificationService = notificationService;
        System.out.println("Grade service created");
    }

    @PostConstruct
    public void onServiceStarted() {
        System.out.println("Grade service started");
    }

    @PreDestroy
    public void onServiceDestroy() {
        System.out.println("Grade service destroyed");
    }

    public void assignGrade(Student student, Course course, double gradeValue) {
        notificationService.notify(student, gradeBook.assignGrade(student, course, gradeValue));
    }
}
