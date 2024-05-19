package app.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class JournalDTO {
    public String progressEntry, exerciseEntry;
    public Date entryDate;
    public double weight;

    public JournalDTO(String progressEntry, String exerciseEntry, Date entryDate, double weight) {
        this.progressEntry = progressEntry;
        this.exerciseEntry = exerciseEntry;
        this.entryDate = entryDate;
        this.weight = weight;
    }
}
