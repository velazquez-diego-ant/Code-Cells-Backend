package code.cells.InstaRama.com.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "contact_form")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontact_form")
    private Integer id;

    @Column(name = "character_type", length = 50)
    private String characterType;

    @Column(name = "message", length = 250)
    private String message;

    @Column(name = "email", length = 150)
    private String email;

    // Relación ManyToOne con User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_iduser", nullable = false)
    private User user;

}