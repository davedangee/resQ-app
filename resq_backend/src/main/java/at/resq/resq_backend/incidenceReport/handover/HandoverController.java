package at.resq.resq_backend.incidenceReport.handover;


import at.resq.resq_backend.incidenceReport.handover.dto.HandoverRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 25.04.2026
 * Time: 17:44
 */

@RestController
@RequestMapping("api/v1/incidenceReport/{reportId}/handover")
@RequiredArgsConstructor
@Slf4j
public class HandoverController {
    private final HandoverService handoverService;

    @GetMapping
    public ResponseEntity<Handover> getByReportId(@PathVariable Long reportId) {
        try {
            return ResponseEntity.ok(handoverService.getByReportId(reportId));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Handover> create(@PathVariable Long reportId, @RequestBody @Valid HandoverRequestDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(handoverService.create(reportId, dto));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Handover> update(@PathVariable Long reportId, @RequestBody @Valid HandoverRequestDto dto) {
        try {
            return ResponseEntity.ok(handoverService.update(reportId, dto));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long reportId) {
        try {
            handoverService.delete(reportId);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
