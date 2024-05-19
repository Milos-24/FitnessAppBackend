package app.entities;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private Boolean locked;

    @Column
    private Boolean enabled;

    @Lob
    @Column
    private byte[] avatar;

    @ManyToOne
    @JoinColumn(name = "user_type_id", nullable = false)
    private UserType userType;

    @Column
    private String city;


}
