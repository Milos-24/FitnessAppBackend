package app.controller;

import app.dtos.JournalDTO;
import app.entities.Journal;
import app.repositories.UserRepository;
import app.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/journal")
@CrossOrigin(origins = "http://localhost:4200")
public class JournalController {

    private final JournalService journalService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> addJournalEntry(@RequestBody JournalDTO journal,@RequestParam int userId)
    {
        Journal journalEntry = Journal.builder()
                .date(journal.getEntryDate())
                .exerciseInfo(journal.getExerciseEntry())
                .weight(journal.getWeight())
                .progressInfo(journal.getProgressEntry())
                .user(userRepository.findById(userId).orElse(null))
                .build();

        return journalService.addJournalEntry(journalEntry);
    }


    @GetMapping("/{user_id}")
    public List<JournalDTO> getJournal(@PathVariable("user_id") int user_id) {
        return journalService.getJournal(user_id);
    }
}
