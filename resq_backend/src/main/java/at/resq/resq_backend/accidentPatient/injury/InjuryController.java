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

@RequestMapping("api/v1/accidentPatient/{patientId}/injury")
@RestController
@RequiredArgsConstructor
@Slf4j
public class InjuryController {
    private final InjuryService injuryService;

    @GetMapping
    public ResponseEntity<Iterable<Injury>> getAllByPatientId(@PathVariable Long patientId) {
        try {
            return ResponseEntity.ok(injuryService.getAllByPatientId(patientId));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Injury> getByIdAndPatientId(@PathVariable Long patientId, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(injuryService.getByIdAndPatientId(id, patientId));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Injury> create(@PathVariable Long patientId, @RequestBody @Valid InjuryRequestDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(injuryService.create(patientId, dto));
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
    public ResponseEntity<Injury> update(@PathVariable Long patientId, @PathVariable Long id, @RequestBody @Valid InjuryRequestDto dto) {
        try {
            return ResponseEntity.ok(injuryService.update(id, patientId, dto));
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
    public ResponseEntity<Void> delete(@PathVariable Long patientId, @PathVariable Long id) {
        try {
            injuryService.delete(id, patientId);
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