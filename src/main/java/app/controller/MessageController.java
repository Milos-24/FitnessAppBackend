package app.controller;

import app.dtos.MessageDTO;
import app.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{username}")
    public List<MessageDTO> getMessages(@PathVariable("username") String username) {
        return messageService.getMessages(username);
    }
    @PostMapping
    public ResponseEntity<?> createComment(
            @RequestBody MessageDTO message) {
          return messageService.createMessage(message);
    }
}
