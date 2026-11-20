package pl.edu.agh.to.school.course;


import pl.edu.agh.to.school.student.Student;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private int id;

    private String name;

    private List<Student> students;

    public Course() {}

    public Course(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public void assignStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
