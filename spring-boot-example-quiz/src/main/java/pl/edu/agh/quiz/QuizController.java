package pl.edu.agh.quiz;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("quizes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public String hello() {
        return "Welcome to Quiz Manager!";
    }

    @GetMapping("/{id}/answers")
    public List<AnswerDto> getAnswers(@PathVariable("id") int quizId) {
        try {
            return quizService.getAnswers(quizId).stream()
                    .map(answer -> new AnswerDto(answer.getId(), answer.getPersonName(), answer.getCorrectAnswersCount()))
                    .toList();
        } catch (QuizNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public record AnswerDto(int id, String personName, int correctAnswers) {
    }
}
