package pl.edu.agh.to.school.student;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.grade.GradeRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public StudentService(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentWithIndexNumber(String indexNumber) {
        return studentRepository.findByIndexNumber(indexNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student with index: " + indexNumber + " not found"));
    }

    @Transactional
    public Student addStudentNewDegree(String indexNumber, int gradeValue) {
        Student student = studentRepository.findByIndexNumber(indexNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student with index: " + indexNumber + " not found"));

        Grade grade = new Grade(gradeValue);

        student.giveGrade(grade);
        gradeRepository.save(grade);

        return studentRepository.save(student);
    }
}
