package pl.edu.agh.to.school.grade;

public class Grade {

    private int id;

    private int gradeValue;

    public Grade(int value) {
        this.gradeValue = value;
    }

    public Grade() {
    }

    public int getId() {
        return id;
    }

    public int getGradeValue() {
        return gradeValue;
    }
}
