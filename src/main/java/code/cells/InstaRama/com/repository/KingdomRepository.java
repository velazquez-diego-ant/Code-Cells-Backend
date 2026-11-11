package code.cells.InstaRama.com.repository;

import code.cells.InstaRama.com.models.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom, Long> {
    Optional<Kingdom> findByKingdomAndSubKingdom(String kingdom, String subKingdom);
}