package code.cells.InstaRama.com.repository;

import code.cells.InstaRama.com.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository
        extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryNameIgnoreCase(
            String categoryName
    );
    List<Category> findByKingdomIgnoreCase(
            String kingdom
    );
    boolean existsByCategoryNameIgnoreCase(
            String categoryName
    );
}