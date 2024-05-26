package app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String comment;
    private int fitnessProgramId;
    private String user;
    private Date date;

}
