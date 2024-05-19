package app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "fitness_program")
@Builder
@AllArgsConstructor
public class FitnessProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private String duration;

    @Column
    private String description;

    @Column
    private Integer level;

    @Column
    private Boolean active;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column
    private String location;

    @Column
    private Date date;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "fitnessProgram")
    @JsonBackReference
    private List<Comment> comments;

    @OneToMany(mappedBy = "fitnessProgram")
    @JsonBackReference
    private List<Membership> memberships;

    @OneToMany(mappedBy = "fitnessProgram")
    @JsonBackReference
    private List<ProgramExercise> programExercises;

    public FitnessProgram() {
    }

    public FitnessProgram(int id, String name, Double price, String duration, String description, Integer level, Boolean active, User creator, String location, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.level = level;
        this.active = active;
        this.creator = creator;
        this.location = location;
        this.category = category;
    }

}
