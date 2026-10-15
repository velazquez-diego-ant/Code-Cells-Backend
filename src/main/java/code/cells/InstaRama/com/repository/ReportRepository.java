package code.cells.InstaRama.com.repository;

import code.cells.InstaRama.com.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByUserId(Long userId);
    List<Report> findByPostId(Long postId);
    List<Report> findByCommentId(Long commentId);
    List<Report> findByStatus(String status);
}