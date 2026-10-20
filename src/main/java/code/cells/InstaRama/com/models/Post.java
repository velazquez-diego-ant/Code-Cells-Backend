package code.cells.InstaRama.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpost")
    private Long id;

    @Column
    private String title;

    @Column(name ="photo", columnDefinition = "MEDIUMBLOB")
    @Lob
    private byte[] photo;

    @Column
    private String description;

    @Column(name = "date", nullable = false)
    private Date datetime;

//     Relación con user
    @ManyToOne
    @JoinColumn (name= "user_iduser" , nullable = false)
    @JsonIgnore
    private User user;

    //Al borrar el post se borran sus comentarios (si no, la llave foránea lo impide)
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Comment> comments;

    //Exponemos solo el id y el nombre del autor (no todo el User, que trae el password)
    @JsonProperty("userId")
    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    @JsonProperty("userName")
    public String getUserName() {
        return user != null ? user.getName() : null;
    }


    // Relación con kingdom
//    @ManyToMany
//    @JoinColumn
//    private Kingdom kingdom;
}
