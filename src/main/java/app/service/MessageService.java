package app.service;

import app.dtos.MessageDTO;
import app.entities.Message;
import app.entities.User;
import app.repositories.MessageRepository;
import app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public List<MessageDTO> getMessages(String username) {

        List<Message> messages = messageRepository.findByReceiver(username);

        return messages.stream().map(message ->
            new MessageDTO(message.getSender().getUsername(),message.getReceiver().getUsername(), message.getContent(), message.getTimestamp())
        ).collect(Collectors.toList());
    }

    public ResponseEntity<?> createMessage(MessageDTO message) {
        User sender = userRepository.findByUsername(message.getSender());
        User receiver = userRepository.findByUsername(message.getReceiver());

        Message newMessage = new Message();
        newMessage.setContent(message.getContent());
        newMessage.setTimestamp(new Date());
        newMessage.setSender(sender);
        newMessage.setReceiver(receiver);

        messageRepository.save(newMessage);

        return ResponseEntity.ok().build();
    }
}
