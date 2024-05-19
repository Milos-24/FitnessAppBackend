package app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "user_type")
public class UserType {

    @Id
    @Column(name = "id")
    private int id;

    @Column
    private String type;

    @JsonBackReference
    @OneToMany(mappedBy = "userType")
    private List<User> users;

}
