package pl.edu.agh.quiz;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pl.edu.agh.answer.Answer;
import pl.edu.agh.answer.AnswerRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final CsvImporter csvImporter;

    private final QuizRepository quizRepository;

    private final AnswerRepository answerRepository;

    public QuizService(CsvImporter csvImporter, QuizRepository quizRepository, AnswerRepository answerRepository) {
        this.csvImporter = csvImporter;
        this.quizRepository = quizRepository;
        this.answerRepository = answerRepository;
        System.out.println("Quiz service was initialized : " + csvImporter);
    }

    public List<Answer> getAnswers(int quizId) throws QuizNotFoundException {
        return quizRepository.findById(quizId)
                .map(Quiz::getAnswers)
                .orElseThrow(QuizNotFoundException::new);
    }

    public Optional<Quiz> createQuiz(String quizCsv) {
        try {
            Quiz quiz = new Quiz();
            quizRepository.save(quiz);

            List<Answer> answers = csvImporter.parseQuizAnswers(quizCsv);
            answers.forEach(quiz::addAnswer);
            answerRepository.saveAll(answers);

            return Optional.of(quizRepository.save(quiz));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    @PostConstruct
    public void initQuiz() throws IOException {
        createQuiz(Files.readString(Path.of("quiz1.csv")))
                .ifPresent(quiz -> quiz.getAnswers().forEach(IO::println));
    }
}
