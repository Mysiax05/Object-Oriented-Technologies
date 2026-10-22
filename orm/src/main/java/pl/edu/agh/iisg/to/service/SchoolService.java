package pl.edu.agh.iisg.to.service;

import pl.edu.agh.iisg.to.dao.GradeDao;
import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;
import pl.edu.agh.iisg.to.repository.StudentRepository;
import pl.edu.agh.iisg.to.session.TransactionService;

import java.util.*;

public class SchoolService {

    private final TransactionService transactionService;

    private final StudentRepository studentRepository;

    private final GradeDao gradeDao;

    public SchoolService(TransactionService transactionService, StudentRepository studentRepository, GradeDao gradeDao) {
        this.transactionService = transactionService;
        this.studentRepository = studentRepository;
        this.gradeDao = gradeDao;
    }

    public boolean enrollStudent(final Course course, final Student student) {
        Set<Student> studentSet = course.studentSet();
        Set<Course> courseSet = student.courseSet();

        if (!studentSet.contains(student)) {
            return transactionService.doAsTransaction(() -> {
                studentSet.add(student);
                courseSet.add(course);
                return true;
            }).orElse(false);
        }
        return false;
    }

    public boolean removeStudent(int indexNumber) {
        return transactionService.doAsTransaction(() -> {
            Optional<Student> studentOptional = studentRepository.getById(indexNumber);
            if (studentOptional.isEmpty()) {
                return false;
            }
            Student student = studentOptional.get();

            for (Course course : student.courseSet()) {
                course.studentSet().remove(student);
            }
            student.courseSet().clear();

            for (Grade grade : student.gradeSet()) {
                gradeDao.remove(grade);
            }
            student.gradeSet().clear();

            studentRepository.remove(student);
            return true;
        }).orElse(false);
    }

    public boolean gradeStudent(final Student student, final Course course, final float gradeValue) {
        Set<Grade> gradeSetInCourse = course.gradeSet();
        Set<Grade> gradeSetInStudent = student.gradeSet();

        return transactionService.doAsTransaction(() -> {
            Grade grade = new Grade(student, course, gradeValue);
            Optional<Grade> savedGrade = gradeDao.save(grade);

            if (savedGrade.isEmpty()) {
                return false;
            }
            gradeSetInCourse.add(grade);
            gradeSetInStudent.add(grade);
            return true;
        }).orElse(false);
    }

    public Map<String, List<Float>> getStudentGrades(String courseName) {
        List<Student> studentList = studentRepository.findAllByCourseName(courseName);
        Map<String, List<Float>> studentGrades = new HashMap<>();

        for (Student student: studentList) {
            String studentFullName = student.fullName();

            List<Float> gradesForCourse = student.gradeSet().stream()
                    .filter(grade -> grade.course().name().equals(courseName))
                    .map(Grade::grade)
                    .toList();

            studentGrades.put(studentFullName, gradesForCourse);
        }
    return studentGrades;
    }
}
