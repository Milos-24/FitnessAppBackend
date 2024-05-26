package app.entities;

import lombok.*;

import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "program_exercise")
@Builder
@AllArgsConstructor
public class ProgramExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @ManyToOne
    @JoinColumn(name = "fitness_program_id", nullable = false)
    private FitnessProgram fitnessProgram;


    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column
    private String repetition;

    @Column
    private String duration;

    @Column
    private String link;

    public ProgramExercise() {
    }

}
