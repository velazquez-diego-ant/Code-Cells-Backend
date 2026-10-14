package code.cells.InstaRama.com.models;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long categoryId;
    @Column(
            name = "name_category",
            nullable = false,
            unique = true,
            length = 100
    )
    private String categoryName;
    @Column(
            name = "kingdom",
            nullable = false,
            length = 50
    )
    private String kingdom;
    // Constructor vacío requerido por JPA
    public Category() {
    }
    // Constructor para crear categorías
    public Category(String categoryName, String kingdom) {
        this.categoryName = categoryName;
        this.kingdom = kingdom;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getKingdom() {
        return kingdom;
    }
    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }
}