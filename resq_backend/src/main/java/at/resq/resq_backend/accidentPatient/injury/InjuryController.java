package at.resq.resq_backend.accidentPatient.injury;


import at.resq.resq_backend.accidentPatient.injury.dto.InjuryRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 12:59
 */

@RequestMapping("api/v1/incidenceReport/{reportId}/accidentPatient/injury")
@RestController
@RequiredArgsConstructor
@Slf4j
public class InjuryController {
    private final InjuryService injuryService;

    @GetMapping
    public ResponseEntity<Iterable<Injury>> getAllByPatientId(@PathVariable Long reportId) {
        try {
            return ResponseEntity.ok(injuryService.getAllByReportId(reportId));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Injury> getByIdAndReportId(@PathVariable Long reportId, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(injuryService.getByIdAndReportId(id, reportId));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Injury> create(@PathVariable Long reportId, @RequestBody @Valid InjuryRequestDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(injuryService.create(reportId, dto));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Injury> update(@PathVariable Long reportId, @PathVariable Long id, @RequestBody @Valid InjuryRequestDto dto) {
        try {
            return ResponseEntity.ok(injuryService.update(id, reportId, dto));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long reportId, @PathVariable Long id) {
        try {
            injuryService.delete(id, reportId);
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