package app.service;

import app.dtos.JournalDTO;
import app.entities.Journal;
import app.repositories.JournalRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;
    public ByteArrayOutputStream savePdf(int user_id) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer;

        try {
            writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            Font titleFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph title = new Paragraph("Fitness Progress Diary", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Paragraph dateTime = new Paragraph("Generated on: " + dateFormat.format(currentDate));
            dateTime.setAlignment(Element.ALIGN_CENTER);
            document.add(dateTime);
            document.add(new Paragraph("\n"));

            List<Journal> journals = journalRepository.findByUserId(user_id);

            journals.forEach(
                    journal -> {
                        try {
                            document.add(new Paragraph("Progress: " + journal.getProgressInfo()));
                            document.add(new Paragraph("Exercise info: "+journal.getExerciseInfo()));
                            document.add(new Paragraph("Date:"+ journal.getDate()));
                            document.add(new Paragraph("====================================="));
                        } catch (DocumentException e) {
                            throw new RuntimeException(e);
                        }

                    }
            );


            document.close();
            writer.close();

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        return outputStream;
    }

    public List<JournalDTO> getJournal(int user_id)
    {
        List<Journal> journal = journalRepository.findByUserId(user_id);
        return journal.stream().map(
                journalDto -> new JournalDTO(journalDto.getProgressInfo(),journalDto.getExerciseInfo(),journalDto.getDate(),journalDto.getWeight()))
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> addJournalEntry(Journal journal) {
        journalRepository.save(journal);

        return ResponseEntity.ok().build();
    }
}
