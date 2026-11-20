package pl.edu.agh.to.school.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudents() {
        return List.of(new Student("Jan", "Kowalski", LocalDate.now(), "123456"));
    }

}
