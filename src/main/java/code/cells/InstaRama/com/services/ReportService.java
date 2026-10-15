package code.cells.InstaRama.com.services;

import lombok.AllArgsConstructor;
import code.cells.InstaRama.com.dtos.ReportRequest;
import code.cells.InstaRama.com.exceptions.ResourceNotFoundException;
import code.cells.InstaRama.com.models.Comment;
import code.cells.InstaRama.com.models.Post;
import code.cells.InstaRama.com.models.Report;
import code.cells.InstaRama.com.models.User;
import code.cells.InstaRama.com.repository.CommentRepository;
import code.cells.InstaRama.com.repository.PostRepository;
import code.cells.InstaRama.com.repository.ReportRepository;
import code.cells.InstaRama.com.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public List<Report> getReportsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User with id " + userId + " not found");
        }
        return reportRepository.findByUserId(userId);
    }

    public Report getReportById(Long reportId) {
        return reportRepository.findById(reportId).orElseThrow(
                () -> new ResourceNotFoundException("Report with id " + reportId + " not found")
        );
    }

    public Report createReport(ReportRequest reportRequest) {
        User user = userRepository.findById(reportRequest.userId()).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + reportRequest.userId() + " not found")
        );

        Post post = null;
        if (reportRequest.postId() != null) {
            post = postRepository.findById(reportRequest.postId()).orElseThrow(
                    () -> new ResourceNotFoundException("Post with id " + reportRequest.postId() + " not found")
            );
        }

        Comment comment = null;
        if (reportRequest.commentId() != null) {
            comment = commentRepository.findById(reportRequest.commentId()).orElseThrow(
                    () -> new ResourceNotFoundException("Comment with id " + reportRequest.commentId() + " not found")
            );
        }

        Report report = new Report();
        report.setType(reportRequest.type());
        report.setEntityType(reportRequest.entityType());
        report.setReferenceLink(reportRequest.referenceLink());
        report.setUser(user);
        report.setPost(post);
        report.setComment(comment);

        if (reportRequest.status() != null) {
            report.setStatus(reportRequest.status());
        }

        return reportRepository.save(report);
    }

    public Report updateReportById(Long reportId, ReportRequest reportRequest) {
        Report reportStored = getReportById(reportId);

        if (reportRequest.type() != null) reportStored.setType(reportRequest.type());
        if (reportRequest.entityType() != null) reportStored.setEntityType(reportRequest.entityType());
        if (reportRequest.referenceLink() != null) reportStored.setReferenceLink(reportRequest.referenceLink());
        if (reportRequest.status() != null) reportStored.setStatus(reportRequest.status());

        return reportRepository.save(reportStored);
    }

    public Report deleteReportById(Long reportId) {
        Report report = getReportById(reportId);
        reportRepository.delete(report);
        return report;
    }
}
