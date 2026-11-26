package pl.edu.agh.to.school.student;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.agh.to.school.SchoolConfigurator;
import pl.edu.agh.to.school.grade.GradeRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentService studentService;

    @MockitoBean
    private SchoolConfigurator schoolConfigurator;

    @Test
    void isEveryStudentListed() {
        // given
        Student kowalski = new Student("Jan", "Kowalski", LocalDate.now(), "123456");
        Student budynek = new Student("Piotr", "Budynek", LocalDate.now(), "6547891");
        Student menczystaty = new Student("Henryk", "Menczystaty", LocalDate.now(), "848565");

        var expected = List.of(kowalski, budynek, menczystaty);
        studentRepository.saveAll(expected);

        // when
        List<Student> actual = studentService.getStudents();

        // then
        assertEquals(expected, actual);
    }

    @Test
    void isStudentReturnedByIndexNumber() {
        // given
        var indexNumber = "123456";
        Student expected = new Student("Jan", "Kowalski", LocalDate.now(), indexNumber);
        studentRepository.save(expected);

        // when
        var actual = studentService.getStudentWithIndexNumber(indexNumber);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void notExistingIndexNumberShouldThrowException() {
        // given
        Student expected = new Student("Jan", "Kowalski", LocalDate.now(), "123456");
        studentRepository.save(expected);

        // when/then
        assertThrows(ResponseStatusException.class,
                () -> studentService.getStudentWithIndexNumber("123"));
    }
}