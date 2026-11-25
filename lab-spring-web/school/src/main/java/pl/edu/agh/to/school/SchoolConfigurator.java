package pl.edu.agh.to.school;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.course.CourseRepository;
import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.grade.GradeRepository;
import pl.edu.agh.to.school.student.Student;
import pl.edu.agh.to.school.student.StudentRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class SchoolConfigurator {

    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;

    public SchoolConfigurator(StudentRepository studentRepository, GradeRepository gradeRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        Student kowalski = new Student("Jan", "Kowalski", LocalDate.now(), "123456");
        Student budynek = new Student("Piotr", "Budynek", LocalDate.now(), "6547891");
        Student menczystaty = new Student("Henryk", "Menczystaty", LocalDate.now(), "848565");

        var to = new Course("Technologie obiektowe");
        var po = new Course("Programowanie obiektowe");

        to.assignStudent(kowalski);
        to.assignStudent(budynek);

        po.assignStudent(menczystaty);

        Grade grade1 = new Grade(5);
        kowalski.giveGrade(grade1);

        Grade grade2 = new Grade(4);
        kowalski.giveGrade(grade2);

        Grade grade3 = new Grade(3);
        budynek.giveGrade(grade3);

        Grade grade4 = new Grade(2);
        menczystaty.giveGrade(grade4);

        gradeRepository.saveAll(List.of(grade1, grade2, grade3, grade4));
        studentRepository.saveAll(List.of(kowalski, budynek, menczystaty));
        courseRepository.saveAll(List.of(to, po));
    }
}
