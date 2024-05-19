package app.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @ManyToOne
    @JoinColumn(name = "fitness_program_id", nullable = false)
    private FitnessProgram fitnessProgram;

    @Column(name = "card", length = 45)
    private String card;

    @ManyToOne
    @JoinColumn(name = "payment_type_id", nullable = false)
    private Payment paymentType;

    @Column(name = "date")
    private Date date;

    public Membership() {}

    public Membership(User user, FitnessProgram fitnessProgram, String card, Payment paymentType, Date date) {
        this.user = user;
        this.fitnessProgram = fitnessProgram;
        this.card = card;
        this.paymentType = paymentType;
        this.date = date;
    }
}
