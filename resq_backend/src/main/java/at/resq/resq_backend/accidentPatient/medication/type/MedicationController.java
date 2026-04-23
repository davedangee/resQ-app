package at.resq.resq_backend.accidentPatient.medication.type;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

/**
 * Project: resQ-app-v1
 * Created by: Leitner David
 * Date: 23.04.2026
 * Time: 08:29
 */

@RestController
@RequestMapping("api/v1/medication")
@RequiredArgsConstructor
@Slf4j
public class MedicationController {
    private final MedicationService medicationService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Medication>> getAllMedications() {
        try {
            return ResponseEntity.ok(medicationService.getAllMedications());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(medicationService.getMedicationById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
