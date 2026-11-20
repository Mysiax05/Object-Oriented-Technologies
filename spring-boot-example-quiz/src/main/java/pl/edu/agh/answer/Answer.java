package pl.edu.agh.answer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import pl.edu.agh.quiz.Quiz;

@Entity
public class Answer {

    @Id
    @GeneratedValue
    private int id;

    private String personName;

    private int correctAnswersCount;

//    @JsonIgnore tak nie robimy
    @ManyToOne
    private Quiz quiz;

    public Answer(String personName, int correctAnswersCount) {
        this.personName = personName;
        this.correctAnswersCount = correctAnswersCount;
    }

    public Answer() {

    }

    public int getId() {
        return id;
    }

    public String getPersonName() {
        return personName;
    }

    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "correctAnswersCount=" + correctAnswersCount +
                ", personName='" + personName + '\'' +
                ", id=" + id +
                '}';
    }
}
