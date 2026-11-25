package pl.edu.agh.to.school.course;

import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDto mapToCourserDto(Course course) {
        return new CourseDto(course.getId(), course.getName());
    }
}
