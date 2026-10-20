package code.cells.InstaRama.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    //Nunca se manda en las respuestas de la API
    @JsonIgnore
    @Column(name = "password_hash", nullable = false, length = 250)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "account_status", nullable = false)
    private Integer accountStatus;

    @Column(nullable = false, length = 50)
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> post;
}
