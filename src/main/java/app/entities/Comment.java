package app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "fitness_program_id", nullable = false)
    private FitnessProgram fitnessProgram;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String comment;



    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Comment() {
    }

    public Comment(FitnessProgram fitnessProgram, User user, String comment, Date date) {
        this.fitnessProgram = fitnessProgram;
        this.user = user;
        this.comment = comment;
        this.date = date;
    }
}
