package code.cells.InstaRama.com.services;

import code.cells.InstaRama.com.dtos.ReportRequest;
import code.cells.InstaRama.com.exceptions.ResourceNotFoundException;
import code.cells.InstaRama.com.models.Report;
import code.cells.InstaRama.com.models.User;
import code.cells.InstaRama.com.repository.CommentRepository;
import code.cells.InstaRama.com.repository.PostRepository;
import code.cells.InstaRama.com.repository.ReportRepository;
import code.cells.InstaRama.com.repository.UserRepository;
import lombok.AllArgsConstructor;
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

    public List<Report> getReportsByPostId(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post with id " + postId + " not found");
        }
        return reportRepository.findByEntityTypeAndReferenceLink("POST", buildReferenceLink("POST", postId));
    }

    public List<Report> getReportsByCommentId(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment with id " + commentId + " not found");
        }
        return reportRepository.findByEntityTypeAndReferenceLink("COMMENT", buildReferenceLink("COMMENT", commentId));
    }

    public Report getReportById(Long reportId) {
        return reportRepository.findById(reportId).orElseThrow(
                () -> new ResourceNotFoundException("Report with id " + reportId + " not found")
        );
    }

    public Report createReportByUserId(Long userId, ReportRequest reportRequest) {
        //? 1.- Verificar que existe el usuario que reporta.
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + userId + " not found")
        );
        //? 2.- Verificar que existe lo que se está reportando (post o comentario).
        validateEntityExists(reportRequest.entityType(), reportRequest.entityId());
        //? 3.- Mapear el dto. El link lo arma el servidor; el status nace en PENDING (@PrePersist).
        Report report = new Report();
        report.setEntityType(reportRequest.entityType());
        report.setEntityId(reportRequest.entityId());
        report.setReferenceLink(buildReferenceLink(reportRequest.entityType(), reportRequest.entityId()));
        report.setType(reportRequest.type());
        report.setUser(user);
        //? 4.- Guardar.
        return reportRepository.save(report);
    }

    public Report updateReportById(Long reportId, ReportRequest reportRequest) {
        Report reportStored = getReportById(reportId);
        // Solo se puede cambiar el motivo y el status; el reporte no cambia de objetivo.
        if (reportRequest.type() != null) reportStored.setType(reportRequest.type());
        if (reportRequest.status() != null) reportStored.setStatus(reportRequest.status());
        return reportRepository.save(reportStored);
    }

    public Report deleteReportById(Long reportId) {
        Report report = getReportById(reportId);
        reportRepository.delete(report);
        return report;
    }

    private void validateEntityExists(String entityType, Long entityId) {
        switch (entityType) {
            case "POST" -> {
                if (!postRepository.existsById(entityId)) {
                    throw new ResourceNotFoundException("Post with id " + entityId + " not found");
                }
            }
            case "COMMENT" -> {
                if (!commentRepository.existsById(entityId)) {
                    throw new ResourceNotFoundException("Comment with id " + entityId + " not found");
                }
            }
            default -> throw new IllegalArgumentException("entityType must be POST or COMMENT");
        }
    }

    // Único lugar donde se define el formato del link: si cambia, solo se cambia aquí.
    private String buildReferenceLink(String entityType, Long entityId) {
        return switch (entityType) {
            case "POST" -> "/api/posts/" + entityId;
            case "COMMENT" -> "/api/comments/" + entityId;
            default -> throw new IllegalArgumentException("entityType must be POST or COMMENT");
        };
    }
}