package code.cells.InstaRama.com.repository;

import code.cells.InstaRama.com.models.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface KingdomRepository
        extends JpaRepository<Kingdom, Long> {
    // Buscar todos los registros de un reino
    List<Kingdom> findByKingdomIgnoreCase(String kingdom);
    // Buscar todos los registros de una subcategoría
    List<Kingdom> findBySubKingdomIgnoreCase(String subKingdom);
    // Buscar una combinación específica reino + subreino
    Optional<Kingdom> findByKingdomIgnoreCaseAndSubKingdomIgnoreCase(
            String kingdom,
            String subKingdom
    );
    // Verificar si existe una combinación reino + subreino
    boolean existsByKingdomIgnoreCaseAndSubKingdomIgnoreCase(
            String kingdom,
            String subKingdom
    );
}