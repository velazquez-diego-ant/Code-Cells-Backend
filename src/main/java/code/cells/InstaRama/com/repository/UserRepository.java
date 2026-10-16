package code.cells.InstaRama.com.repository;

import code.cells.InstaRama.com.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
    Optional<User> findByEmail(
            String email
    );
}
