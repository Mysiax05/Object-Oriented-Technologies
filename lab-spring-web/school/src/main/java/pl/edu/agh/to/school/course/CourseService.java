package pl.edu.agh.to.school.course;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.student.StudentDto;
import pl.edu.agh.to.school.student.StudentMapper;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper, StudentMapper studentMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.studentMapper = studentMapper;
    }

    public List<CourseDto> getCourses() {
        return courseRepository
                .findAll()
                .stream()
                .map(courseMapper::mapToCourserDto)
                .toList();
    }

    public List<StudentDto> getStudents(int id) {
        return courseRepository
                .findStudentsByCourseId(id)
                .stream()
                .map(studentMapper::mapToStudentDto)
                .toList();
    }
}
