package at.resq.resq_backend.accidentPatient;

import at.resq.resq_backend.accidentPatient.dto.AccidentPatientDtos;
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
 * Date: 22.04.2026
 * Time: 14:29
 */

@RequestMapping("api/v1/accidentPatient")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AccidentPatientController {
    private final AccidentPatientService accidentPatientService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<AccidentPatient>> getAllAccidentPatients() {
        try {
            List<AccidentPatient> accidentPatientList = accidentPatientService.getAllAccidentPatients();
            return ResponseEntity.ok(accidentPatientList);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccidentPatient> getAccidentPatientById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(accidentPatientService.getAccidentPatientById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<AccidentPatient> createAccidentPatient(@RequestBody @Valid AccidentPatientDtos.AccidentPatientRequestDto dto) {
        try {
            AccidentPatient created = accidentPatientService.createAccidentPatient(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<AccidentPatient> updateAccidentPatient(@PathVariable Long id, @RequestBody @Valid AccidentPatientDtos.AccidentPatientRequestDto dto) {
        try {
            AccidentPatient updated = accidentPatientService.updateAccidentPatient(id, dto);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccidentPatient(@PathVariable Long id) {
        try {
            accidentPatientService.deleteAccidentPatient(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
