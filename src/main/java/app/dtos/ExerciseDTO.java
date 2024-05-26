package app.dtos;

import lombok.Data;

@Data
public class ExerciseDTO {
    public String duration, name, repetition, link;

    public ExerciseDTO(String duration, String name, String repetition, String link) {
        this.duration = duration;
        this.name = name;
        this.repetition = repetition;
        this.link = link;
    }
}
