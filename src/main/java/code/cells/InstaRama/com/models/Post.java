package code.cells.InstaRama.com.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(name ="photo", columnDefinition = "MEDIUMBLOB")
    @Lob
    private byte[] photo;

    @Column
    private String description;

    @Column
    private Date datetime;

    // Relación con user
//    @ManyToOne
//    @JoinColumn
//    private User user;

    // Relación POST -> KINGDOM
    @ManyToOne
    @JoinColumn(name = "kingdom_idKingdoms", nullable = false)
    private Kingdom kingdom;