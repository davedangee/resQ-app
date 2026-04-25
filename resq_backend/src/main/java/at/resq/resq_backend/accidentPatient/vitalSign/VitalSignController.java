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
        return ResponseEntity.ok(vitalSignService.getAllByReportId(reportId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalSign> getByIdAndPatientId(@PathVariable Long reportId, @PathVariable Long id) {
        return ResponseEntity.ok(vitalSignService.getByIdAndReportId(id, reportId));
    }

    @PostMapping
    public ResponseEntity<VitalSign> create(@PathVariable Long reportId, @RequestBody @Valid VitalSignRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vitalSignService.create(reportId, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VitalSign> update(@PathVariable Long reportId, @PathVariable Long id, @RequestBody @Valid VitalSignRequestDto dto) {
        return ResponseEntity.ok(vitalSignService.update(id, reportId, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long reportId, @PathVariable Long id) {
        vitalSignService.delete(id, reportId);
        return ResponseEntity.noContent().build();
    }
}
