package pl.edu.agh.to.school.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "students")
public class StudentController {
    @GetMapping
    public List<Student> getStudents() {
        return List.of(new Student("Jan", "Kowalski", LocalDate.now(), "123456"));
    }
}