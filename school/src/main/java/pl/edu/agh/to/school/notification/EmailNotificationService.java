package pl.edu.agh.to.school.notification;

import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.student.Student;

@Profile("dev")
@DependsOn("greenMailHandler")
@Service
public class EmailNotificationService implements NotificationService {

    private final JavaMailSender mailSender;

    public EmailNotificationService(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    @Override
    public void notify(Student student, Grade grade) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("nauczyciel@agh.edu.pl");
        msg.setTo(student.getEmail());
        msg.setSubject("New grade: " + grade.course().getName());
        msg.setText("You received a new grade: " + grade.value());
        mailSender.send(msg);
    }
}
