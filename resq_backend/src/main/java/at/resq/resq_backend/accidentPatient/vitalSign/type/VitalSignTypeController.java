package at.resq.resq_backend.accidentPatient.vitalSign.type;


import at.resq.resq_backend.accidentPatient.medication.type.Medication;
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
 * Time: 10:40
 */

@RestController
@RequestMapping("api/v1/vitalSignType")
@RequiredArgsConstructor
@Slf4j
public class VitalSignTypeController {
    private final VitalSignTypeService vitalSignTypeService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<VitalSignType>> getAllVitalSignTypes() {
        try {
            return ResponseEntity.ok(vitalSignTypeService.getAllVitalSignTypes());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalSignType> getVitalSignById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(vitalSignTypeService.getVitalSignById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
