package pl.edu.agh.to.school.notification;

import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.student.Student;

public interface NotificationService {

    void notify(Student student, Grade grade);
}
