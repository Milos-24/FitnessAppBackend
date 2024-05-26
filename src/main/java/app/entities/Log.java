package app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Builder
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "date")
    public Date date;

    @Column(name = "event", length = 45)
    public String event;

    public Log() {

    }
}
