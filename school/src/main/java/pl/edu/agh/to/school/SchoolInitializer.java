package pl.edu.agh.to.school;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.student.Student;
import pl.edu.agh.to.school.student.StudentService;

import java.time.LocalDate;

@Component
public class SchoolInitializer {

    private final StudentService studentService;

    public SchoolInitializer(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostConstruct
    public void initialize() {
        Student student = new Student(
                "Hubert",
                "Myszka",
                LocalDate.of(2137, 6, 7),
                "2137",
                "hmyszka@student.agh.edu.pl");

        Course course = new Course("Technologie Obiektowe");

        studentService.assignGrade(student, course, 5.0);
    }
}
