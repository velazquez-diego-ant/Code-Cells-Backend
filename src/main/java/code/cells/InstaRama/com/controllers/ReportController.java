package code.cells.InstaRama.com.controllers;

import code.cells.InstaRama.com.dtos.ReportRequest;
import code.cells.InstaRama.com.models.Report;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import code.cells.InstaRama.com.services.ReportService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping(path = "reports") //* http://localhost:8080/api/reports
    public ResponseEntity<List<Report>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping(path = "user/{userId}/reports") //* http://localhost:8080/api/user/1/reports
    public ResponseEntity<List<Report>> getReportsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(reportService.getReportsByUserId(userId));
    }

    @GetMapping(path = "posts/{postId}/reports") //* http://localhost:8080/api/posts/1/reports
    public ResponseEntity<List<Report>> getReportsByPostId(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(reportService.getReportsByPostId(postId));
    }

    @GetMapping(path = "comments/{commentId}/reports") //* http://localhost:8080/api/comments/1/reports
    public ResponseEntity<List<Report>> getReportsByCommentId(@PathVariable("commentId") Long commentId) {
        return ResponseEntity.ok(reportService.getReportsByCommentId(commentId));
    }

    @GetMapping(path = "reports/{reportId}") //* http://localhost:8080/api/reports/1
    public ResponseEntity<Report> getReportById(@PathVariable("reportId") Long reportId) {
        return ResponseEntity.ok(reportService.getReportById(reportId));
    }

    @PostMapping(path = "user/{userId}/reports")
    public ResponseEntity<Report> createReportByUserId(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody ReportRequest reportRequest) {
        Report report = reportService.createReportByUserId(userId, reportRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/reports/{id}")
                .buildAndExpand(report.getId())
                .toUri();
        return ResponseEntity.created(location).body(report);
    }

    @PutMapping(path = "reports/{reportId}")
    public ResponseEntity<Report> updateReportById(
            @PathVariable("reportId") Long reportId,
            @RequestBody ReportRequest reportRequest) {
        return ResponseEntity.ok(reportService.updateReportById(reportId, reportRequest));
    }

    @DeleteMapping(path = "reports/{reportId}")
    public ResponseEntity<Report> deleteReportById(@PathVariable("reportId") Long reportId) {
        return ResponseEntity.ok(reportService.deleteReportById(reportId));
    }
}