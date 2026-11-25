package pl.edu.agh.to.school.student;

import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto mapToStudentDto(Student student) {
        return new StudentDto(
                student.getFirstName(),
                student.getLastName(),
                student.getBirthDate(),
                student.getIndexNumber());
    }
}
