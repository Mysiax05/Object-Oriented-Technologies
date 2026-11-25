package pl.edu.agh.to.school.course;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.to.school.student.StudentDto;

import java.util.List;

@RestController
@RequestMapping(path = "courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDto> getGrades() {
        return courseService.getCourses();
    }

    @GetMapping(path = "/{id}")
    public List<StudentDto> getStudentsFromCourse(@PathVariable int id) {
        return courseService.getStudents(id);
    }
}
