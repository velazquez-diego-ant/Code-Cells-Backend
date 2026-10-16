package code.cells.InstaRama.com.models;
import jakarta.persistence.*;
@Entity
@Table(name = "Kingdoms")
public class Kingdom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idKingdoms")
    private Long idKingdoms;
    @Column(name = "kingdom", nullable = false)
    private String kingdom;
    @Column(name = "subKingdom", nullable = false)
    private String subKingdom;

    // Constructor vacío requerido por JPA
    public Kingdom() {
    }

    // Constructor para crear un registro de Kingdom
    public Kingdom(String kingdom, String subKingdom) {
        this.kingdom = kingdom;
        this.subKingdom = subKingdom;
    }
    public Long getIdKingdoms() {
        return idKingdoms;
    }
    public void setIdKingdoms(Long idKingdoms) {
        this.idKingdoms = idKingdoms;
    }
    public String getKingdom() {
        return kingdom;
    }
    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }
    public String getSubKingdom() {
        return subKingdom;
    }
    public void setSubKingdom(String subKingdom) {
        this.subKingdom = subKingdom;
    }
}