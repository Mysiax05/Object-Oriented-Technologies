package pl.edu.agh.to.school.course;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.school.student.Student;

import java.time.LocalDate;

@Configuration
public class CourseFactory {

    @Bean
    public Course computerNetworksCourse() {
        var student = new Student("Hubert", "Myszka", LocalDate.of(1990, 11, 7), "22334455", "hmyszka@student.agh.edu.pl");
        var course = new Course("Sieci komputerowe");
        course.enrollStudent(student);
        return course;
    }

    @Bean
    public Course objectOrientedTechnologiesCourse() {
        var student = new Student("Hubert", "Myszka", LocalDate.of(2137, 6, 7), "2137", "hmyszka@student.agh.edu.pl");
        var course = new Course("Technologie Obiektowe");
        course.enrollStudent(student);
        return course;
    }
}
