package pl.edu.agh.to.school.course;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDto> getCourses() {
        return courseRepository
                .findAll()
                .stream()
                .map(courseMapper::mapToCourserDto)
                .toList();
    }

}
