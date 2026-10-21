package code.cells.InstaRama.com.repository;

import code.cells.InstaRama.com.models.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContactFormRepository extends JpaRepository<ContactForm, Integer> {
    List<ContactForm> findByUserId(Long userId);
}