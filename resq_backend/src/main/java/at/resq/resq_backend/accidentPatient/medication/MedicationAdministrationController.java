package at.resq.resq_backend.accidentPatient.medication;


import at.resq.resq_backend.accidentPatient.medication.dto.MedicationAdministrationRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 08:35
 */

@RequestMapping("api/v1/accidentPatient/{patientId}/medicationAdministration")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MedicationAdministrationController {
    private final MedicationAdministrationService medicationAdministrationService;

    @GetMapping
    public ResponseEntity<Iterable<MedicationAdministration>> getAllByPatientId(@PathVariable Long patientId) {
        try {
            return ResponseEntity.ok(medicationAdministrationService.getAllByPatientId(patientId));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationAdministration> getByIdAndPatientId(@PathVariable Long patientId, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(medicationAdministrationService.getByIdAndPatientId(id, patientId));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/medication/{medicationId}")
    public ResponseEntity<MedicationAdministration> create(@PathVariable Long patientId, @PathVariable Long medicationId, @RequestBody @Valid MedicationAdministrationRequestDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(medicationAdministrationService.create(patientId, medicationId, dto));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicationAdministration> update(@PathVariable Long patientId, @PathVariable Long id, @RequestBody @Valid MedicationAdministrationRequestDto dto) {
        try {
            return ResponseEntity.ok(medicationAdministrationService.update(id, patientId, dto));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long patientId, @PathVariable Long id) {
        try {
            medicationAdministrationService.delete(id, patientId);
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