package pl.edu.agh.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import pl.edu.agh.answer.Answer;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    private List<Answer> answers = new ArrayList<>();

    public Quiz() {}

    public int getId() {
        return id;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuiz(this);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
