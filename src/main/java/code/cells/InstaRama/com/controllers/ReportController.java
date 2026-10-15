package code.cells.InstaRama.com.controllers;

import lombok.AllArgsConstructor;
import code.cells.InstaRama.com.dtos.ReportRequest;
import code.cells.InstaRama.com.models.Report;
import code.cells.InstaRama.com.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class ReportController {




    @RestController
    @RequestMapping(path = "/api")
    @AllArgsConstructor
    public class ReportController {

        private final ReportService reportService;

        @GetMapping(path = "reports")
        public ResponseEntity<List<Report>> getAllReports() {
            return ResponseEntity.ok(reportService.getAllReports());
        }

        @GetMapping(path = "user/{userId}/reports")
        public ResponseEntity<List<Report>> getReportsByUserId(@PathVariable("userId") Long userId) {
            return ResponseEntity.ok(reportService.getReportsByUserId(userId));
        }

        @GetMapping(path = "reports/{reportId}")
        public ResponseEntity<Report> getReportById(@PathVariable("reportId") Long reportId) {
            return ResponseEntity.ok(reportService.getReportById(reportId));
        }

        @PostMapping(path = "reports")
        public ResponseEntity<Report> createReport(@RequestBody ReportRequest reportRequest) {
            Report report = reportService.createReport(reportRequest);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(report.getId())
                    .toUri();
            return ResponseEntity.created(location).body(report);
        }

        @PutMapping(path = "reports/{reportId}")
        public ResponseEntity<Report> updateReportById(@PathVariable("reportId") Long reportId, @RequestBody ReportRequest reportRequest) {
            Report report = reportService.updateReportById(reportId, reportRequest);
            return ResponseEntity.ok(report);
        }

        @DeleteMapping(path = "reports/{reportId}")
        public ResponseEntity<Report> deleteReportById(@PathVariable("reportId") Long reportId) {
            Report report = reportService.deleteReportById(reportId);
            return ResponseEntity.ok(report);
        }
    }
}
