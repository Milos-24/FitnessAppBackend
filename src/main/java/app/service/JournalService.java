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
