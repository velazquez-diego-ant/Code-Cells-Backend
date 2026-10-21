package code.cells.InstaRama.com.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact_form")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactForm {
    //* CAMPOS EN => name = "" que deben ser relacionados con la BD */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontact_form")
    private Integer id;

    @Column(name = "character_type", length = 50)
    private String characterType;

    @Column(name = "message", length = 250)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Column(name = "email", length = 150)
    private String email;
    
}