package pl.edu.agh.to.school.grade;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.student.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GradeBook {

    private final Map<String, List<Grade>> studentGrades = new HashMap<>();

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

    public Grade assignGrade(Student student, Course course, double gradeValue) {
        Grade grade = new Grade(course, gradeValue);
        studentGrades.computeIfAbsent(student.getIndexNumber(), _ -> new ArrayList<>()).add(grade);
        return grade;
    }

    public Map<String, List<Grade>> getStudentGrades() {
        return studentGrades;
    }
}
