package app.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {
    String sender, receiver,content;
    Date date;

    public MessageDTO(String sender, String receiver, String content, Date date) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = date;
    }
}
