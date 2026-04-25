package at.resq.resq_backend.incidenceReport;


import at.resq.resq_backend.incidenceReport.dto.IncidenceReportRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 24.04.2026
 * Time: 20:16
 */

@RestController
@RequestMapping("api/v1/incidenceReport")
@RequiredArgsConstructor
@Slf4j
public class IncidenceReportController {
    private final IncidenceReportService incidenceReportService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<IncidenceReport>> getAllIncidenceReports() {
        try {
            List<IncidenceReport> incidenceReportList = incidenceReportService.getAllIncidenceReports();
            return ResponseEntity.ok(incidenceReportList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenceReport> getIncidenceReportById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(incidenceReportService.getIncidenceReportById(id));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<IncidenceReport> createIncidenceReport(
            @RequestBody @Valid IncidenceReportRequestDto dto
    ) {
        try {
            IncidenceReport created = incidenceReportService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<IncidenceReport> patchIncidenceReport(
            @PathVariable Long id,
            @RequestBody JsonNode patchNode
    ) {
        try {
            IncidenceReport updated = incidenceReportService.patch(id, patchNode);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncidenceReport(@PathVariable Long id) {
        try {
            incidenceReportService.deleteIncidenceReport(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
