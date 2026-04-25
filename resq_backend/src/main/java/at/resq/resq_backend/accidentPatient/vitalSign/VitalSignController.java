package at.resq.resq_backend.accidentPatient.vitalSign;


import at.resq.resq_backend.accidentPatient.vitalSign.dto.VitalSignRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 10:14
 */

@RequestMapping("/api/v1/incidenceReport/{reportId}/accidentPatient/vitalSign")
@RestController
@RequiredArgsConstructor
@Slf4j
public class VitalSignController {
    private final VitalSignService vitalSignService;

    @GetMapping
    public ResponseEntity<Iterable<VitalSign>> getAllByPatientId(@PathVariable Long reportId) {
        try {
            return ResponseEntity.ok(vitalSignService.getAllByReportId(reportId));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalSign> getByIdAndPatientId(@PathVariable Long reportId, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(vitalSignService.getByIdAndReportId(id, reportId));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<VitalSign> create(@PathVariable Long reportId, @RequestBody @Valid VitalSignRequestDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(vitalSignService.create(reportId, dto));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VitalSign> update(@PathVariable Long reportId, @PathVariable Long id, @RequestBody @Valid VitalSignRequestDto dto) {
        try {
            return ResponseEntity.ok(vitalSignService.update(id, reportId, dto));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long reportId, @PathVariable Long id) {
        try {
            vitalSignService.delete(id, reportId);
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
