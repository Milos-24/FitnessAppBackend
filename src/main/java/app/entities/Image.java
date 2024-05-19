package app.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Lob
    @Column(name = "image")
    private byte[] image;


    @ManyToOne
    @JoinColumn(name = "fitness_program_id", nullable = false)
    private FitnessProgram fitnessProgram;

}
