package code.cells.InstaRama.com.repository;

import code.cells.InstaRama.com.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,  Long> {
}
