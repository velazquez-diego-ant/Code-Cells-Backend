package code.cells.InstaRama.com.repository;

import code.cells.InstaRama.com.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,  Long> {
    List<Post> findAllByUser_Id(Long id);
}
