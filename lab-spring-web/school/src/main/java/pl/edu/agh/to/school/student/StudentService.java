package pl.edu.agh.to.school.student;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentWithIndexNumber(String indexNumber) {
        return studentRepository.findByIndexNumber(indexNumber)
                .orElseThrow(() -> new EntityNotFoundException("Student with indexNumber: " + indexNumber + " not found"));
    }
}
