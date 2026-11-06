package pl.edu.agh.school.persistence;

import pl.edu.agh.school.SchoolClass;
import pl.edu.agh.school.Teacher;

import java.util.List;

public interface SerializablePersistenceManagerInterface {

    void saveTeachers(List<Teacher> teacherList);

    List<Teacher> loadTeachers();

    void saveClasses(List<SchoolClass> classes);

    List<SchoolClass> loadClasses();
}
