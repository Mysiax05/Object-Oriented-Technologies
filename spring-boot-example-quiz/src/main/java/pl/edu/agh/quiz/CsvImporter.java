package pl.edu.agh.quiz;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import pl.edu.agh.answer.Answer;

import java.io.IOException;
import java.util.List;

@Component
public class CsvImporter {
    
    public CsvImporter() {
        System.out.println("CSV Importer was initialized");
    }

    public List<Answer> parseQuizAnswers(String csvData) throws IOException {
        CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT
                .withDelimiter(';')
                .withFirstRecordAsHeader());
        List<CSVRecord> records = parser.getRecords();

        return records.stream()
                .map(record -> {
                    String personName = record.get("Nazwa zwierzaka");
                    int correctAnswersCount = Integer.parseInt(record.get("Suma punktów"));
                    return new Answer(personName, correctAnswersCount);
                }).toList();
    }
}
