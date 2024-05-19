package app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String category;

    public Category() {
    }

    @JsonBackReference
    @OneToMany(mappedBy = "category", fetch=FetchType.EAGER)
    public List<FitnessProgram> fitnessPrograms;

    public Category(int id, String category) {
        this.id = id;
        this.category = category;
    }

}
