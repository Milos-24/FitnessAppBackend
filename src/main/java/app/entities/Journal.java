package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private Double weight;

    @Column
    private String exerciseInfo;

    @Column
    private String progressInfo;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Journal() {
    }

    public Journal(int id, Double weight, String exerciseInfo, String progressInfo, User user) {
        this.id = id;
        this.weight = weight;
        this.exerciseInfo = exerciseInfo;
        this.progressInfo = progressInfo;
        this.user = user;
    }

}
