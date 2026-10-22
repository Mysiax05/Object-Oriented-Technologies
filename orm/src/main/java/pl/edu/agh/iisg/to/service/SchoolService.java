package pl.edu.agh.iisg.to.service;

import pl.edu.agh.iisg.to.dao.CourseDao;
import pl.edu.agh.iisg.to.dao.GradeDao;
import pl.edu.agh.iisg.to.dao.StudentDao;
import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;
import pl.edu.agh.iisg.to.session.TransactionService;

import java.util.*;

public class SchoolService {

    private final TransactionService transactionService;

    private final StudentDao studentDao;

    private final CourseDao courseDao;

    private final GradeDao gradeDao;

    public SchoolService(TransactionService transactionService, StudentDao studentDao, CourseDao courseDao, GradeDao gradeDao) {
        this.transactionService = transactionService;
        this.studentDao = studentDao;
        this.courseDao = courseDao;
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
            Optional<Student> studentOptional = studentDao.findByIndexNumber(indexNumber);
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

            studentDao.remove(student);
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
        Optional<Course> courseOptional = courseDao.findByName(courseName);
        if (courseOptional.isEmpty()) {
            return Collections.emptyMap();
        }
        Course course = courseOptional.get();
        Map<String, List<Float>> studentGrades = new HashMap<>();

        for (Grade grade : course.gradeSet()) {
            String studentFullName = grade.student().fullName();

            studentGrades.computeIfAbsent(studentFullName, _ -> new ArrayList<>())
                    .add(grade.grade());
        }
        return studentGrades;
    }
}
