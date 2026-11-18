package pl.edu.agh.to.school;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.student.StudentService;

import java.util.List;

@Component
public class SchoolInitializer {

    private final StudentService studentService;

    private final String appVersion;

    private final Course computerNetworksCourse;
    private final Course objectOrientedTechnologiesCourse;

    private final List<Course> courseList;

    public SchoolInitializer(StudentService studentService,
                             Course computerNetworksCourse,
                             Course objectOrientedTechnologiesCourse,
                             List<Course> courseList,
                             @Value("${school.app.version}") String appVersion) {
        this.studentService = studentService;
        this.computerNetworksCourse = computerNetworksCourse;
        this.objectOrientedTechnologiesCourse = objectOrientedTechnologiesCourse;
        this.courseList = courseList;
        this.appVersion = appVersion;
    }

    @PostConstruct
    public void initComputerNetworksCourse() {
        computerNetworksCourse.getStudents()
                .forEach(student -> studentService.assignGrade(student, computerNetworksCourse, 4.5));
    }

    @PostConstruct
    public void initObjectOrientedTechnologiesCourse() {
        objectOrientedTechnologiesCourse.getStudents()
                .forEach(student -> studentService.assignGrade(student, objectOrientedTechnologiesCourse, 5.0));
    }

    @PostConstruct
    public void initAllCourses() {
        courseList.forEach(course -> course.getStudents()
                .forEach(student -> studentService.assignGrade(student, course, 2.0))
        );
    }

    @PostConstruct
    public void appVersion() {
        System.out.println("Application version: " + appVersion);
    }
}
