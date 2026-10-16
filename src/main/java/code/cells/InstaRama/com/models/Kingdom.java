package code.cells.InstaRama.com.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "kingdoms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kingdom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String kingdom;

    @Column(nullable = false, length = 100)
    private String subKingdom;
}