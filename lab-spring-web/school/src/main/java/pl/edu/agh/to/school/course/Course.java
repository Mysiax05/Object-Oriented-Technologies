package pl.edu.agh.to.school.course;


import jakarta.persistence.*;
import pl.edu.agh.to.school.student.Student;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    private final List<Student> students = new ArrayList<>();

    public Course() {}

    public Course(String name) {
        this.name = name;
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
