package pl.edu.agh.to.school.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent(@RequestParam(required = false) String indexNumber) {
        if (indexNumber != null) {
            return List.of(studentService.getStudentWithIndexNumber(indexNumber));
        } else {
            return studentService.getStudents();
        }
    }
}