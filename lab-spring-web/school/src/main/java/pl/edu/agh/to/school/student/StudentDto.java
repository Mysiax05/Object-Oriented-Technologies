package pl.edu.agh.to.school.student;

import java.time.LocalDate;

public record StudentDto (
        String firstName,
        String lastName,
        LocalDate birthDate,
        String indexNumber
)
{}
