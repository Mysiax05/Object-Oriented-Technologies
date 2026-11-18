package pl.edu.agh.to.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.grade.GradeBook;
import pl.edu.agh.to.school.student.Student;
import pl.edu.agh.to.school.student.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class SchoolApplicationTests {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeBook gradeBook;

    @MockitoBean
    private SchoolInitializer schoolInitializer;

    @Test
    void isGradingMechanismWorking() {
        //given
        var dummyStudent = new Student(
                "Dummy",
                "Student",
                LocalDate.now(),
                "1",
                "student@student.agh.edu.pl"
        );
        var dummyCourse = new Course("Dummy Course");
        dummyCourse.enrollStudent(dummyStudent);

        //when
        studentService.assignGrade(dummyStudent, dummyCourse, 2.0);

        //then
        var expectedGrades = Map.of("1", List.of(new Grade(dummyCourse, 2.0)));
        assertEquals(expectedGrades, gradeBook.getStudentGrades());
    }

}
