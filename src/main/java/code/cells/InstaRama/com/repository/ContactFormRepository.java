package code.cells.InstaRama.com.repository;

import code.cells.InstaRama.com.models.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactFormRepository extends JpaRepository<ContactForm, Integer> {
    // Método para buscar todos los mensajes de un usuario específico
    List<ContactForm> findByUserId(Long userId);
}