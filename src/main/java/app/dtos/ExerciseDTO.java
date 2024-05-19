package app.dtos;

import lombok.Data;

@Data
public class ExerciseDTO {
    public String duration, name, repetition;

    public ExerciseDTO(String duration, String name, String repetition) {
        this.duration = duration;
        this.name = name;
        this.repetition = repetition;
    }
}
